#include<reg51.h>

sbit but = P1^7;

// 延时函数
void delay(int x){
	int i,j;
	for(i=x;i>0;i--)
		for(j=200;j>0;j--);
	
}

void main(){
	  int temp = 0x00;
		P2 = temp;
	while(1){
		if(temp > 0xff) temp = 0x00;
			if(but == 0){
			// 按键消抖
			delay(20);
			if(but == 0){
				temp +=1;
				// 等待按键抬起
				while(but == 0);
				P2 = temp;
			}
	
		}
	}	
}