#include<reg51.h>
#include<math.h>

#define	LED	P2	


void delay(int x){
	int y;
	for(x;x>0;x--)
		for(y=0;y<100;y++);

}
// right
void right(){
	int temp ;
	int i;
	//while(1){
		temp = 0x80;
		for(i = 0;i<8;i++){
		P2 = temp;
		delay(100);
		temp>>=1;
//	}
	}
}
void left(){
	int temp ;
	int i;
	//while(1){
		temp = 0x01;
		for(i = 0;i<8;i++){
		P2 = temp;
		delay(100);
		temp<<=1;
	}
	//}
}

void ff2(){
	int temp[8] = {0x80,0x40,0x20,0x10,0x08,0x04,0x02,0x01};
	int i;
	while(1){
		for(i = 0;i<9;i++){
		P2 = temp[i];
		delay(100);
	}
	}
}

void main(){
	
	if(1);
	else;
	
	while(1){
		right();
		left();
	
	}

}
