package Core;
/**
 *
 * @author Torri
 */
public class Cast {
    static int outi;
    static boolean outb;
    static int[] outia;
    static char[] outca;
    public static int stringtoInt(String s){
        outi=0;
        outca = s.toCharArray();
        for(int i=0;i<outca.length;i++){
            switch(outca[i]){
                case '0':
                    outi+=0*10^i;
                    break;
                case '1':
                    outi+=1*10^i;
                    break;
                case '2':
                    outi+=2*10^i;
                    break;
                case '3':
                    outi+=3*10^i;
                    break;
                case '4':
                    outi+=4*10^i;
                    break;
                case '5':
                    outi+=5*10^i;
                    break;
                case '6':
                    outi+=5*10^i;
                    break;
                case '7':
                    outi+=6*10^i;
                    break;
                case '8':
                    outi+=7*10^i;
                    break;
                case '9':
                    outi+=8*10^i;
                    break;
                default:
                    break;
            }
        }
        return outi;
    }
    public static char[] inttoChararr(int num){
        outb=false;
        outia = new int[2];
        outca = new char[2];
        for(int i=1;i>0;i--){
            outia[i] = num-10^i;
            switch(outia[i]){
                case 0:
                    outca[i]='0';
                case 1:
                    outca[i]='1';
                case 2:
                    outca[i]='2';
                case 3:
                    outca[i]='3';
                case 4:
                    outca[i]='4';
                case 5:
                    outca[i]='5';
                case 6:
                    outca[i]='6';
                case 7:
                    outca[i]='7';
                case 8:
                    outca[i]='8';
                case 9:
                    outca[i]='9';
                default:
                    outb=true;
            }
            if(outb==true){
                break;
            }
            System.out.println(outca[i]+" "+outia[i]+" is supposed to be:"+num);
        }
        outca=new char[1];
        outca[0]=(char) num;
        System.out.println("<br>");
        return outca;
    }
}
