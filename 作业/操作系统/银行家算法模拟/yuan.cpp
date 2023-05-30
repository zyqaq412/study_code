#include<iostream>
#include<string>
using namespace std;
#define n 5
#define m 3
int available[m] = { 2,3,3 }, alloc[n][m], need[n][m], request[n][m];
void init()
{
	alloc[0][0] = 2;  alloc[0][1] = 1;  alloc[0][2] = 2;
	alloc[1][0] = 4;  alloc[1][1] = 0;  alloc[1][2] = 2;
	alloc[2][0] = 3;  alloc[2][1] = 0;  alloc[2][2] = 5;
	alloc[3][0] = 2;  alloc[3][1] = 0;  alloc[3][2] = 4;
	alloc[4][0] = 3;  alloc[4][1] = 1;  alloc[4][2] = 4;
 
	need[0][0] = 3;  need[0][1] = 4;  need[0][2] = 7;
	need[1][0] = 1;  need[1][1] = 3;  need[1][2] = 4;
	need[2][0] = 0;  need[2][1] = 0;  need[2][2] = 3;
	need[3][0] = 2;  need[3][1] = 2;  need[3][2] = 1;
	need[4][0] = 1;  need[4][1] = 1;  need[4][2] = 0;
 
	request[0][0] = 2;  request[0][1] = 0;  request[0][2] = 1;
	request[1][0] = 0;  request[1][1] = 3;  request[1][2] = 4;
	request[2][0] = 0;  request[2][1] = 0;  request[2][2] = 2;
	request[3][0] = 1;  request[3][1] = 0;  request[3][2] = 1;
	
}
void display()
{ int i,j,k;
	cout << "资源 可分配" << endl;
	for ( i = 0; i < m; i++)
	{
		cout << (char)('A' + i) << "      " << available[i] << endl;
	}
	cout << endl;
	cout << "进程 资源 已分配 需求"<<endl;
	for (j = 0; j < n; j++)
	{
 
		for (k = 0; k < m; k++)
		{
			cout << j+1<< "     " <<(char)('A' + k)<< "     " << alloc[j][k] << "     " << need[j][k] << endl;
		}
	}
	cout << endl;
}
bool judge()
{
	int i, j, tag = n;
	bool non;
	int work[m], finish[n];
	//work 储存当前 available 的值
	//finish 判断当前进程是否为安全序列
	for (i = 0; i<m; i++) work[i] = available[i]; //初始化
	for (i = 0; i<n; i++) finish[i] = 0; //初始化     
	while (tag--)
	{
		for (i = 0; i<n; i++)    
		{
			if (finish[i] == 0) //判断是否为安全序列
			{
				non = true;
				for (j = 0; j < m; j++)
				{
					if (need[i][j] > work[j]) non = false; 
				}
				//需求资源 大于当前可用剩余资源时 当前循环结束
				//当前线程 需求资源 小于当前可用剩余资源时 进入下面的循环
				if(non)
				{
					for (j = 0; j < m; j++)  
					{
						
						//释放 当前线程 已分配的资源
						work[j] = work[j] + alloc[i][j];
						//将当前线程标记为安全序列 不再参与后面的循环
						finish[i] = 1;
					}
				}
			}
		}
	}
	//判断 n 个线程是否都为安全序列
		//是返回 1 不是返回0
	non = true;
	for (i = 0; i < n; i++)
	{	
		if (finish[i] != 1) non = false;
	}
	//0 == false
	// 大于0 == true
	if (non) return 1;
	else return 0;
}
void deal(int k)
{
	for (int j = 0; j < m; j++)
	{
		//判断 k 进程 请求的 available[j] 资源 是否大于 k进程需要的资源
		if (request[k][j] > need[k][j])
		{
	
			cout << " 非法！！！" << endl;
			return;
		}
	}
	//如果 k 进程 请求的 available[j] 资源 小于 k进程需要的资源
	//判断 请求资源数是否小于 可用剩余资源
	for (int w = 0; w< m; w++)
	{
		if (request[k][w] > available[w])
		{
	
			cout << " 阻塞！！！" << endl;
			return;
		}
	}
	for (int p = 0; p < m; p++)  //试探性分配
	{
		available[p] -= request[k][p];
		alloc[k][p] += request[k][p];
		need[k][p] -= request[k][p];
	}
	if (judge())//判断是否为安全状态
	{
		//返回1
		cout << " 资源分配成功！！！" << endl << endl;
		display();
	}
	else
	{
		//返回0
		cout << " 资源分配后系统处于不安全状态！！！" << endl;
		for (int j = 0; j < m; j++)  //试探性分配还原
		{
			available[j] += request[k][j];
			alloc[k][j] -= request[k][j];
			need[k][j] += request[k][j];
		}
	}
	cout << endl;
}
int main()
{
	init();//初始化数据
	display();//查看资源 进程详情
	cout << "a: 进程p2请求资源（0，3，4）  " ;
	deal(1);//尝试分配资源
	cout << "b: 进程p4请求资源（1，0，1）  " ;
	deal(3);  
	cout << "c: 进程p1请求资源（2，0，1）  " ;
	deal(0);
	cout << "d: 进程p3请求资源（0，0，2）  " ;
	deal(2);
	return 0;
}
