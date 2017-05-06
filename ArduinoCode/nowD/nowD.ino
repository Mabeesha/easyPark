
const int bfloor1In = 13;     // the number of the pushbutton pin
const int bfloor1Out = 12;     // the number of the pushbutton pin
const int bfloor2In =11 ;     // the number of the pushbutton pin
const int bfloor2Out = 10;     // the number of the pushbutton pin


int buttonState1 = 0;         // variable for reading the pushbutton status
int buttonState2 = 0; 
int buttonState3 = 0; 
int buttonState4 = 0; 

bool carDetect1to2=false;
bool carDetect2to1=false;

bool buttonPre1=false;
bool buttonPre2=false;
bool buttonPre3=false;
bool buttonPre4=false;

int floor1In=0;
int floor2In=0;
int floor1Out=0;
int floor2Out=0;
//const int gateDecod =  13;
const int input0Decod= 6;
const int gateMux= 4;
const int outputMux =3;
const int input0Mux =2;
const int output =5;




long duration, distance0,distance1,Sensor0,Sensor1;

long microsecondsToCentimeters(long microseconds)
{
return microseconds / 29 / 2;
}
void setup() {
  

Serial.begin (9600);

pinMode(gateMux, OUTPUT);

pinMode(input0Decod, OUTPUT);
pinMode(outputMux, INPUT);
pinMode(input0Mux, OUTPUT);
pinMode(output,OUTPUT);

pinMode(bfloor1In, INPUT);
  pinMode(bfloor1Out, INPUT);
  pinMode(bfloor2In, INPUT);
  pinMode(bfloor2Out, INPUT);
}



void loop() {
  buttonState1 = digitalRead(bfloor1In);
     if (buttonState1 == HIGH && buttonPre1==false) {
      buttonPre1=true;   
  }
 // delay(100);
  if(buttonState1==LOW && buttonPre1==true){
   
    floor1In++;
    buttonPre1=false;
   } 
  

  
  buttonState2 = digitalRead(bfloor1Out);
     if (buttonState2 == HIGH && buttonPre2==false) {
      buttonPre2=true;   
  }
 // delay(100);
  if(buttonState2==LOW && buttonPre2==true){
    
    floor1Out++;
    buttonPre2=false;
   } 
  
  buttonState3 = digitalRead(bfloor2In);  
     if (buttonState3 == HIGH && buttonPre3==false) {      
      buttonPre3=true;   
  }
 // delay(100);
  if(buttonState3==LOW && buttonPre3==true){
     
    floor2In++; 
    buttonPre3=false;
   } 

  buttonState4 = digitalRead(bfloor2Out);
     if (buttonState4 == HIGH && buttonPre4==false) {
      buttonPre4=true;   
  }
 // delay(100);
  if(buttonState4==LOW && buttonPre4==true){
   
    floor2Out++;
    buttonPre4=false;
   } 
  // put your main code here, to run repeatedly:
digitalWrite(gateMux, HIGH);
digitalWrite(input0Decod, LOW);
digitalWrite(input0Mux, LOW);
digitalWrite(output, HIGH);
delayMicroseconds(10);
digitalWrite(output, LOW);
digitalWrite(gateMux, LOW);
duration = pulseIn(outputMux, HIGH);
distance0= microsecondsToCentimeters(duration);
delay(100);


digitalWrite(gateMux, HIGH);
digitalWrite(input0Decod, HIGH);
digitalWrite(input0Mux, HIGH);
digitalWrite(output, HIGH);
delayMicroseconds(10);
digitalWrite(output, LOW);
digitalWrite(gateMux, LOW);
duration = pulseIn(outputMux, HIGH);
distance1= microsecondsToCentimeters(duration);
//Serial.println(distance0);
if(carDetect1to2==false && distance0<20){    
    carDetect1to2 = true;
}
if(carDetect1to2==true && distance0>20){     
    carDetect1to2 = false;
    floor1Out++;
    floor2In++;
}

    
if(carDetect2to1==false && distance1<20){  
    carDetect2to1 = true;
}
if(carDetect2to1==true && distance1>20){    
    carDetect2to1 = false;
    floor2Out++;
    floor1In++;
}
//  Serial.print(floor1In);
//  Serial.print(" ");
//  Serial.print(floor1Out);
//  Serial.print(" ");
  Serial.print(floor1In-floor1Out);
  Serial.print(" ");
//  Serial.print(floor2In);
//  Serial.print(" ");
//  Serial.print(floor2Out);
//  Serial.print(" ");
  Serial.print(floor2In-floor2Out);
  

//Serial.print("DATA,TIME,TIMER,");
//Serial.print(distance0);Serial.print("<<<<<distance0  distance1>>>>>");Serial.print(distance1);
Serial.println();


//delay(100);
}
