#include <stdio.h>
#include <stdlib.h>
#include<string.h>
typedef struct ssjjff
{
    char name[10];
    double arriveTime;  //����ʱ��
    double serveTime;   //����ʱ�� 
    double startTime;   //��ʼʱ�� 
    double endTime;      //���ʱ��
double zhouzhuan;    //��תʱ��
} sjf;
sjf s[101];
void input(sjf *p,int N);
void Sort(sjf *p,int N);
void output(sjf *p,double arriveTime,double serveTime,double startTime,double endTime,double zhouzhuan,int N);
void deal(sjf *p,double arriveTime,double serveTime,double startTime,double endTime,double zhouzhuan,int N);
void SJF(sjf *p,int N);
 
int main()
{
    int N;
    printf("      ����ҵ���ȵ����㷨             \n");
    printf("�������������");
    scanf("%d",&N);
    input(s,N);
    sjf *p = s;
    SJF(p,N);
    return 0;
}
 
void input(sjf *p,int N) // ��������
{
    int i;
    for (i=0; i<N; i++)
    {
        printf("�����%d���������ơ�����ʱ�䡢����ʱ�䣺\n",i+1);
        scanf("%s%lf%lf",p[i].name,&p[i].arriveTime,&p[i].serveTime);
 
    }
}
 
void output(sjf *p,double arriveTime,double serveTime,double startTime,double endTime,double zhouzhuan,int N) // �������
{
    int j;
    printf("ִ��˳��:\n");
    printf("%s",p[0].name);
    for(j=1; j<N; j++)
    {
        printf("-->%s",p[j].name);
    }
    printf("\n");
    printf("\n������        ����ʱ��        ����ʱ��        ��ʼʱ��        ����ʱ��        ��תʱ��        ��Ȩ��תʱ��\n");
    
    double dqzz = 0.00;
    for(j=0; j<N; j++)
    {
        
        dqzz = p[j].zhouzhuan / p[j].serveTime;//��Ȩ��תʱ��
       
        printf("%s\t\t%-.2f\t\t%-.2f\t\t%-.2f\t\t%-.2f\t\t%-.2f\t\t%.2f\t\n\n"
        ,p[j].name, p[j].arriveTime, p[j].serveTime, p[j].startTime, p[j].endTime,p[j].zhouzhuan,dqzz);
    }
}
 
 
 
void deal(sjf *p,double arriveTime,double serveTime,double startTime,double endTime,double zhouzhuan,int N) //��������
{
    int k;
    for(k=0; k<N; k++)
    {
        if(k == 0)
        {
            p[k].startTime = p[k].arriveTime;
            p[k].endTime = p[k].arriveTime + p[k].serveTime;
        }
        else
        {
            p[k].startTime = p[k-1].endTime;
            p[k].endTime = p[k-1].endTime + p[k].serveTime;
        }
 
    }
    for(k=0; k<N; k++)
    {
        p[k].zhouzhuan = p[k].endTime - p[k].arriveTime;//��תʱ�� = ����ʱ��-����ʱ��
    }
}
 
void Sort(sjf *p,int N)// ����ʱ������
{
    int i,j;
    for(i=0; i<N; i++)
        for(j=0; j<i+1; j++)
        {
            if( p[i].arriveTime < p[j].arriveTime)
            {
                sjf temp;
                temp = p[i];
                p[i] = p[j];
                p[j] = temp;
            }
        }
}
 
void SJF(sjf *p,int N)
{
    double arriveTime=0,serveTime=0,startTime=0,endTime=0,zhouzhuan=0;//�Խṹ����
    Sort(p,N);
    int n,m;
    for(m=0; m<N-1; m++)
    {
        if(m==0)
            p[m].endTime= p[m].arriveTime+ p[m].serveTime;
        else
            p[m].endTime= p[m-1].endTime+ p[m].serveTime;
        int i=0;
        for(n=m+1; n<=N-1; n++)
        {
            if( p[n].arriveTime <= p[m].endTime)//�ж��ڴ���ÿ�����֮���ж��ٵ���Ľ���
                i++;
        }
        float min= p[m+1].serveTime;
        int next=m+1;//m+1=n
        int k;
        for(k=m+1; k<m+i; k++) //�ҳ������Ľ�������С�Ľ���
        {
            if( p[k+1].serveTime <min)
            {
                min=p[k+1].serveTime;
                next=k+1;
            }
        }
        sjf temp;
        temp=p[m+1];
        p[m+1]=p[next];
        p[next]=temp;
    }
    deal(p,arriveTime,serveTime,startTime,endTime,zhouzhuan,N);
    output(p,arriveTime,serveTime,startTime,endTime,zhouzhuan,N);
 
}