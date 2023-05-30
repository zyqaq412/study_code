#include<reg51.h>
#include<math.h>

#define	LED	P2	
int temp = 1,a=0;

void time_init(void){
	TMOD=0x01;
	TH0=(65536-20000)/256;
	TL0=(65536-20000)%256;
	ET0=1;
	EA=1;
	TR0=1;
}


void pao(){
	P2 = temp;
}



void main(){
	time_init();
	while(1){
		pao();
	}
}
void T0_time() interrupt 1{
//	TR0=0;
	TH0=(65536-20000)/256;
	TL0=(65536-20000)%256;
//	TR0=1;
	a++;
	if(a==50){
		a=0;
		temp<<=1;
		if(temp>0x80)
			temp=1;
	}
}
