#include<reg51.h>
#include<intrins.h>

sbit SW7 = P1^4;
sbit lb = P1^7;

void delay(int x){
	int y;
	for(x;x>0;x--)
		for(y=0;y<100;y++);

}

void main(){
	unsigned int temp;
	// P2M1 = 0x00;
	// P2M0 = 0xff;
	temp = 0x01;
	while(1){
		if(SW7 == 1){
            P2= temp;
            temp = _cror_(temp,1);
				lb  = 0;
						delay(300);
						
		}else{
			P2= temp;
			temp = _crol_(temp,1);
			lb  = 1;
			delay(300);
		}
	}
}

