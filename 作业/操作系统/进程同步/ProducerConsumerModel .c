#include <pthread.h>
#include <time.h>
#include <stdio.h>
#include <unistd.h>
#include <semaphore.h>
#include<stdlib.h>

const int cap_default = 10;
int array[10] = {0};

//生产者关心空位置资源
sem_t blank_sem;

//消费者关心数据资源
sem_t data_sem;

//生产者生产数据的位置
int p_step;

//消费者消费数据的位置
int c_step;

//加两把锁，实现多生产者多消费者
pthread_mutex_t c_mtx;
pthread_mutex_t p_mtx;

void *consumer(void *args)
{
	while (1)
	{
		//申请信号量
		sem_wait(&data_sem);
		//加锁
		pthread_mutex_lock(&c_mtx);
		int data = array[c_step];
		sem_post(&blank_sem);
		c_step++;
		if (c_step == 10)
		{
			c_step = 0;
		}
		printf("线程[%d]消费数据%d\n", pthread_self(), data);
		pthread_mutex_unlock(&c_mtx);
		sleep(1);
	}
}

void *producter(void *args)
{
	while (1)
	{
		sem_wait(&blank_sem);
		//加锁
		pthread_mutex_lock(&p_mtx);
		int data = rand() % 20 + 1;
		array[p_step] = data;
		sem_post(&data_sem);
		p_step++;
		if (p_step == 10)
		{
			p_step = 0;
		}
		printf("线程[%lu]生产数据%d\n", pthread_self(), data);
		pthread_mutex_unlock(&p_mtx);
		sleep(1);
	}
}

int main()
{
	p_step = 0;
	c_step = 0;

	sem_init(&blank_sem, 0, cap_default);
	sem_init(&data_sem, 0, 0);

	pthread_mutex_init(&c_mtx, NULL);
	pthread_mutex_init(&p_mtx, NULL);

	srand(time(NULL));


    pthread_t c1, c2, c3, p1, p2, p3;
    pthread_create(&c1, NULL, consumer, NULL);
    pthread_create(&c2, NULL, consumer, NULL);
    pthread_create(&c3, NULL, consumer, NULL);
    pthread_create(&p1, NULL, producter, NULL);
    pthread_create(&p2, NULL, producter, NULL);
    pthread_create(&p3, NULL, producter, NULL);

    pthread_join(c1, NULL);
    pthread_join(c2, NULL);
    pthread_join(c3, NULL);
    pthread_join(p1, NULL);
    pthread_join(p2, NULL);
    pthread_join(p3, NULL);



	sem_destroy(&blank_sem);
	sem_destroy(&data_sem);

	pthread_mutex_destroy(&c_mtx);
	pthread_mutex_destroy(&p_mtx);

	return 0;
}
