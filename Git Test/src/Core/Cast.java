package Core;
/**
 *
 * @author Torri
 */
public class Cast {
    static int outi;
    static String outs;
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
    public static int[] stringstoInts(String[] s){
        int[] outi = new int[s.length];
        for(int j=0;j<s.length;j++){
            outca = s[j].toCharArray();
            for(int i=0;i<outca.length;i++){
                switch(outca[i]){
                    case '0':
                        outi[j]+=0*10^i;
                        break;
                    case '1':
                        outi[j]+=1*10^i;
                        break;
                    case '2':
                        outi[j]+=2*10^i;
                        break;
                    case '3':
                        outi[j]+=3*10^i;
                        break;
                    case '4':
                        outi[j]+=4*10^i;
                        break;
                    case '5':
                        outi[j]+=5*10^i;
                        break;
                    case '6':
                        outi[j]+=5*10^i;
                        break;
                    case '7':
                        outi[j]+=6*10^i;
                        break;
                    case '8':
                        outi[j]+=7*10^i;
                        break;
                    case '9':
                        outi[j]+=8*10^i;
                        break;
                    default:
                        break;
                }
            }
        }
        return outi;
    }
    public static String inttoString(int num){
        outs = Integer.toString(num);
        return outs;
    }
    public static String[] intstoStrings(int[] num){
        String[] outs = new String[num.length];
        for(int i=0;i<num.length;i++){
            outs[i] = Integer.toString(num[i]);
        }
        return outs;
    }
}
