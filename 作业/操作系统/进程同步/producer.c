#include <semaphore.h>
#include <stdio.h>   
#include <stdlib.h>     
#include <string.h>
#include <pthread.h> 
#include <unistd.h>
//#define MAX 256
//char *buffer;
sem_t empty;    //定义同步信号量empty
sem_t full;	//定义同步信号量full
sem_t mutex1;	//定义互斥信号量mutex
//一个互斥信号不能让生产者，消费者线程同时进行
//使用添加一个 互斥信号
 sem_t mutex2; 
int array[10] = {0};//替代char *buffer;
//生产者生产数据的位置
int p_step;

//消费者消费数据的位置
int c_step;
void * producer()	//生产者
{
  while(1)
  {
	
	sem_wait(&empty);	//empty的P操作
	sem_wait(&mutex1);	//mutex的P操作 相当于加锁
	//生产 用随机数当数据 对20取余限制大小
	int data = rand() % 20 + 1;
	sem_post(&full);	//full的V操作 
	array[p_step] = data;
	//对10取余防止数组越界
	p_step = (p_step+1)%10;
	printf("线程[%lu]生产数据%d\n", pthread_self(), data);
	sem_post(&mutex1);	//mutex的V操作 
	
	sleep(1);
	
	
	
	//printf("input something to buffer:");
	//buffer=(char *)malloc(MAX);	//给缓冲区分配内存空间
	//fgets(buffer,MAX,stdin);	//输入产品至缓冲区
	
  }
}
void * consumer()	//消费者
{
while(1)
{

sem_wait(&full);	//full的P操作
	sem_wait(&mutex2);	//mutex的P操作
	int data = array[c_step];
	sem_post(&empty);	//empty的V操作
	c_step=(c_step+1)%10;
	printf("线程[%d]消费数据%d\n", pthread_self(), data);
	sem_post(&mutex2);	//mutex的V操作
	sleep(1);
  
  
   //printf("read product from buffer:%s",buffer); //从缓冲区中取出产品
 // memset(buffer,0,MAX);		//清空缓冲区

}
}
int main()
{
  pthread_t c1,c2,c3,p1,p2,p3;
  //pthread_t id_producer;
  //pthread_t id_consumer;
  //int ret;
  sem_init(&empty,0,10);	//设置empty到初值为10
  sem_init(&full,0,0);		//设置full到初值为0
  sem_init(&mutex1,0,1);		//设置mutex到初值为1
  sem_init(&mutex2,0,1);	
  pthread_create(&p1,NULL,producer,NULL);  //创建生产者线程
pthread_create(&p2,NULL,producer,NULL);  //创建生产者线程
  pthread_create(&p3,NULL,producer,NULL);  //创建生产者线程
  
  pthread_create(&c1,NULL,consumer,NULL);  //创建消费者线程
 pthread_create(&c2,NULL,consumer,NULL);  //创建消费者线程
  pthread_create(&c3,NULL,consumer,NULL);  //创建消费者线程
  
  pthread_join(p1,NULL);	//等待生产者线程结束
 pthread_join(p2,NULL);	//等待生产者线程结束
  pthread_join(p3,NULL);	//等待生产者线程结束
  pthread_join(c1,NULL);	//等待消费者线程结束
 pthread_join(c2,NULL);	//等待消费者线程结束
 pthread_join(c3,NULL);	//等待消费者线程结束
  sem_destroy(&empty);		//删除信号量
  sem_destroy(&full);
  sem_destroy(&mutex1);
  sem_destroy(&mutex2);
  printf("The End...\n");
}
