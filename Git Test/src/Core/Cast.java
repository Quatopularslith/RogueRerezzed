package Core;
/**
 *
 * @author Torri
 */
public class Cast {
    static int outi;
    static char[] string;
    public static int stringtoInt(String s){
        outi=0;
        string = s.toCharArray();
        for(int i=0;i<string.length;i++){
            switch(string[i]){
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
}
