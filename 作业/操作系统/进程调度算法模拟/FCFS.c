#include<stdio.h>
#include<stdlib.h>
typedef struct PCB      //������̿��ƿ� 
{
    char ID[3];         //���̺� 
    char name[10];      //������ 
    char state;         //����״̬ 
    int arrivetime;     //���̵���ʱ�� 
    int servicetime;    //���̷���ʱ�� 
    int starttime;      //���̿�ʼʱ�� 
    int finishtime;     //�������ʱ�� 
    float turnaroundtime; //������תʱ�� 
    float weightedturnaroundtime;  //���̴�Ȩ��תʱ�� 
    struct PCB *next;   //ָ����һ�����̵�ָ�� 
}pcb;
int time; //��ʱ��
int n;    //���̸���
pcb *head=NULL,*p,*q; //���̵�ָ��
void run_fcfs(pcb *p1)
{
    time=p1->arrivetime>time?p1->arrivetime:time;
    p1->starttime=time;
    printf("\n����ʱ����%d,��ʼ������ҵ%s\n",time,p1->name);
    time+=p1->servicetime;
    p1->state='T';
    p1->finishtime=time;
    p1->turnaroundtime=p1->finishtime-p1->arrivetime;
    p1->weightedturnaroundtime=p1->turnaroundtime/p1->servicetime;
    printf("ID  ����ʱ��  ��ʼʱ��  ����ʱ��  ���ʱ��  ��תʱ��  ��Ȩ��תʱ��\n");
    printf("%s%6d%10d%10d%8d%10.1f%10.2f\n",p1->ID,p1->arrivetime,p1->starttime,p1->servicetime,p1->finishtime,p1->turnaroundtime,p1->weightedturnaroundtime); 
 }  
void find_fcfs() //Ѱ��δ��ɵ�pcb
{
    int i,j;
    p=head;
    for(i=0;i<n;i++)
    {
        if(p->state=='F')
        {
            q=p; //��qָ���δ��ɵ�pcb"ժ"���� 
            run_fcfs(q); 
        }
        p=p->next;
        }

   } 

void found_fcfs() //��ý�����Ϣ����������
{
    int num;
    printf("\n���̸���Ϊ��");
    scanf("%d",&n);
    int flag;
    pcb *temp;
    for(num=0;num<n;num++)
    {
        p=(pcb*)malloc(sizeof(pcb));
        printf("���������룺\nID   ������  ����ʱ��  ����ʱ��\n");
scanf("%s\t%s\t%d\t%d",&p->ID,&p->name,&p->arrivetime,&p->servicetime);
//��ʼ������
         p->starttime=0;
        p->finishtime=0;
        p->turnaroundtime=0;
        p->weightedturnaroundtime=0;
        p->next=NULL;
        p->state='F';

        if(head==NULL)
        {
            head=p;
            q=head;
            continue;
        }
        if(p->arrivetime < q->arrivetime){
            printf("%d",0);
            p->next = q;
            head = p;
            q = head;
            continue;
        }else{
            while (q->next != NULL)
            {
                temp = q;
                q=q->next;
                if (p->arrivetime < q->arrivetime)
                {
                    temp->next = p;
                    p->next = q;
                    q= head;
                    flag = 1;
                   break;
                }              
            }
            if(flag) continue;//0Ϊ�٣���0Ϊ��
            //�������� q ָ�� ���һ������
            q->next = p;
            q = head ;
        }
     } 
     time = head->arrivetime;
  }
void main()
{
    printf("�����ȷ���ģ���㷨��");
    found_fcfs();
    p=head;
    find_fcfs(); 
}

// if(head==NULL)
//         {

//             head=p;
//             q=p;
//             time=p->arrivetime;
//         }
//         if(p->arrivetime<time) time=p->arrivetime;
//         q->next=p;
//         p->starttime=0;
//         p->finishtime=0;
//         p->turnaroundtime=0;
//         p->weightedturnaroundtime=0;
//         p->next=NULL;
//         p->state='F';
//         q=p;