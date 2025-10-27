import java.util.Scanner;
public class ssw{
public static void main(String[]args){
Scanner scanner=new Scanner(System.in);
int windowSize=4;
int totalFrames=8;
int sentFrame=0;
while(sentFrame<totalFrames){
for(int i=0;i<windowSize && sentFrame+i<totalFrames;i++)
{
System.out.println("Sent Frame: "+(sentFrame+i));
}
System.out.println("Enter acknowledgement (expected " + sentFrame + "):");
int ack=scanner.nextInt();
if(ack==sentFrame){
sentFrame++;
}else{
System.out.println("Incorrect acknowledgement resending from frame "+sentFrame);
}
}
System.out.println("All frames sent successfully");
scanner.close();
}
}

