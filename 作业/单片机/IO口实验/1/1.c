#include<reg51.h>
//#include<math.h>

sbit SW7 = P1^4;

void delay(int x){
	int y;
	for(x;x>0;x--)
		for(y=0;y<100;y++);

}
// right
int right(int temp){
		P2 = temp;
		delay(300);
		temp>>=1;
		if(temp < 0x01) temp = 0x80;
		return temp;
		

}
int left(int temp){
		P2 = temp;
		delay(300);
		temp<<=1;
		if(temp > 0x80) temp = 0x01;
		return temp;

}

void main(){
	unsigned int temp;
	// P2M1 = 0x00;
	// P2M0 = 0xff;
	temp = 0x80;

	while(1){
		if(SW7 == 1){
		temp=right(temp);
		}else{
		temp=left(temp);
		}
	}
}