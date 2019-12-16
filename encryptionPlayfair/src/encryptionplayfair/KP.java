/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package encryptionplayfair;

/**
 *
 * @author Heni
 */
public class KP {
    public static void main(String[] args) {
        
            
    }
    public KP(){}

    public String olahInputan(String a ){
        String text="";
        //menghilangkan spasi
            for(int i=0; i<a.length();i++){
                String text1=(a.substring(i,i+1));
                if(text1.equals(" ")==false){
                    text=text+text1;
                }
            }
            //cek bagian data yang sama,jika ada ditambah huruf 'x'
            int pjgKunci=text.length();
            for(int i=0; i<pjgKunci; i=i+2){
                if(i==text.length()-1){
                    if(text.length()%2!=0){
                        text=text+"x";
                    }
                }
                String x1=text.substring(i,i+1);
                String x2=text.substring(i+1,i+2);
                if(x1.equalsIgnoreCase(x2)){
                    text=text.substring(0,i+1)+"x"+text.substring(i+1,text.length());
                    pjgKunci++;
                }

            }
        return text;
    }
    public String plaintText(String chipperText,int matrik[][]){
        String plaintText="";
        int aPost[]=new int[2];
        int bPost[]=new int[2];
        int length=0;

        if(chipperText.length()%2!=0){
            //int aa=(int)(Math.random()*25)+1;
            length=chipperText.length()+1;
            chipperText=chipperText+"x";
        }else length=chipperText.length();

            for(int i=0; i<length; i=i+2){
                int a=ConvertToDecimal(chipperText.substring(i,i+1));
                int b=ConvertToDecimal(chipperText.substring(i+1,i+2));
                aPost=post(matrik,a);
                bPost=post(matrik,b);

                if(aPost[0]==bPost[0]){//horizontal
                    int xA=aPost[1]-1;
                    int xB=bPost[1]-1;
                    if(xA==-1){
                        xA=4;
                    }
                    if(xB==-1){
                        xB=4;
                    }
                    plaintText=plaintText+""+ConvertToWord(matrik[aPost[0]][xA]);
                    plaintText=plaintText+""+ConvertToWord(matrik[bPost[0]][xB]);
                }else if(aPost[1]==bPost[1]){//vertikal
                    int xA=aPost[0]-1;
                    int xB=bPost[0]-1;
                    if(xA==-1){
                        xA=4;
                    }
                    if(xB==-1){
                        xB=4;
                    }
                    plaintText=plaintText+""+ConvertToWord(matrik[xA][aPost[1]]);
                    plaintText=plaintText+""+ConvertToWord(matrik[xB][bPost[1]]);
                }else{
                    plaintText=plaintText+""+ConvertToWord(matrik[aPost[0]][bPost[1]]);
                    plaintText=plaintText+""+ConvertToWord(matrik[bPost[0]][aPost[1]]);
                }

            }
        return plaintText;
    }
    public  String chipperText(String plaintText,int matrik[][]){
        String chipperText="";
        int aPost[]=new int[2];
        int bPost[]=new int[2];
        int length=0;
        if(plaintText.length()%2!=0){

            //int aa=(int)(Math.random()*25)+1;
            length=plaintText.length()+1;
            plaintText=plaintText+"x";
        }else length=plaintText.length();

            for(int i=0; i<length; i=i+2){
                int a=ConvertToDecimal(plaintText.substring(i,i+1));
                int b=ConvertToDecimal(plaintText.substring(i+1,i+2));
                aPost=post(matrik,a);
                bPost=post(matrik,b);

                if(aPost[0]==bPost[0]){
                    int xA=aPost[1]+1;
                    int xB=bPost[1]+1;
                    if(xA==5){
                        xA=0;
                    }
                    if(xB==5){
                        xB=0;
                    }
                    chipperText=chipperText+""+ConvertToWord(matrik[aPost[0]][xA]);
                    chipperText=chipperText+""+ConvertToWord(matrik[bPost[0]][xB]);
                }else if(aPost[1]==bPost[1]){
                    int xA=aPost[0]+1;
                    int xB=bPost[0]+1;
                    if(xA==5){
                        xA=0;
                    }
                    if(xB==5){
                        xB=0;
                    }
                    chipperText=chipperText+""+ConvertToWord(matrik[xA][aPost[1]]);
                    chipperText=chipperText+""+ConvertToWord(matrik[xB][bPost[1]]);
                }else{
                    chipperText=chipperText+""+ConvertToWord(matrik[aPost[0]][bPost[1]]);
                    chipperText=chipperText+""+ConvertToWord(matrik[bPost[0]][aPost[1]]);
                }
                
            }
        return chipperText;
    }
    public int[] post(int matrik[][], int a){

        int x[]=new int[2];
        for(int i=0; i<matrik.length; i++){
            for(int j=0; j<matrik.length; j++){
                if(a==matrik[i][j]){
                    x[0]=i;
                    x[1]=j;
                    break;
                }
            }
        }
        return x;

    }
    public int[][] inputMatrik(String aLength){
        int matrik[][]=new int[5][5];
            int count=0;
            int dAngka=1;
            for(int i=0; i<matrik.length; i++){
                for(int j=0; j<matrik.length; j++){
                    if((count+1)!=aLength.length()+1){
                        int angka=ConvertToDecimal(aLength.substring(count,(count+1)));
                        matrik[i][j]=angka;
                        count++;
                    }else{

                        for(int k=0; k<=i;k++){
                            for(int l=0; l<matrik.length;l++){
                                //System.out.println(dAngka+" = "+matrik[k][l]);
                                if(dAngka==matrik[k][l]){
                                    dAngka++;
                                    k=0;l=-1;
                                }
                            }
                        }
                        matrik[i][j]=dAngka;
                        dAngka++;
                    }

                }
            }
            return matrik;

    }
    public String KataKunci(String a){
        String ktKunci="";
                        
            String c[]=new String[a.length()];
            boolean flag=false;
            for(int i=0; i<a.length();i++){
                String b=a.substring(i,(i+1));
                for(int j=0; j<=i;j++){
                    if(b.equalsIgnoreCase(c[j])){
                        flag=true;
                    }
                }
                if(flag==false){
                    c[i]=b;
                }
                flag=false;
                if(c[i]!=null){
                ktKunci=ktKunci+c[i];
                }
                
            }
        return ktKunci;
    }

    public String ConvertToWord(int angka){
        String huruf="";
            if(angka==1)huruf="a";
            else if(angka==2)huruf="b";
            else if(angka==3)huruf="c";
            else if(angka==4)huruf="d";
            else if(angka==5)huruf="e";
            else if(angka==6)huruf="f";
            else if(angka==7)huruf="g";
            else if(angka==8)huruf="h";
            else if(angka==9)huruf="i";
            else if(angka==10)huruf="k";
            else if(angka==11)huruf="l";
            else if(angka==12)huruf="m";
            else if(angka==13)huruf="n";
            else if(angka==14)huruf="o";
            else if(angka==15)huruf="p";
            else if(angka==16)huruf="q";
            else if(angka==17)huruf="r";
            else if(angka==18)huruf="s";
            else if(angka==19)huruf="t";
            else if(angka==20)huruf="u";
            else if(angka==21)huruf="v";
            else if(angka==22)huruf="w";
            else if(angka==23)huruf="x";
            else if(angka==24)huruf="y";
            else if(angka==25)huruf="z";
        return huruf;
    }
    public int ConvertToDecimal(String abjad){
        int angka=0;
            if(abjad.equalsIgnoreCase("a"))angka=1;
            else if(abjad.equalsIgnoreCase("b"))angka=2;
            else if(abjad.equalsIgnoreCase("c"))angka=3;
            else if(abjad.equalsIgnoreCase("d"))angka=4;
            else if(abjad.equalsIgnoreCase("e"))angka=5;
            else if(abjad.equalsIgnoreCase("f"))angka=6;
            else if(abjad.equalsIgnoreCase("g"))angka=7;
            else if(abjad.equalsIgnoreCase("h"))angka=8;
            else if(abjad.equalsIgnoreCase("i") || (abjad.equalsIgnoreCase("j")))angka=9;
            else if(abjad.equalsIgnoreCase("k"))angka=10;
            else if(abjad.equalsIgnoreCase("l"))angka=11;
            else if(abjad.equalsIgnoreCase("m"))angka=12;
            else if(abjad.equalsIgnoreCase("n"))angka=13;
            else if(abjad.equalsIgnoreCase("o"))angka=14;
            else if(abjad.equalsIgnoreCase("p"))angka=15;
            else if(abjad.equalsIgnoreCase("q"))angka=16;
            else if(abjad.equalsIgnoreCase("r"))angka=17;
            else if(abjad.equalsIgnoreCase("s"))angka=18;
            else if(abjad.equalsIgnoreCase("t"))angka=19;
            else if(abjad.equalsIgnoreCase("u"))angka=20;
            else if(abjad.equalsIgnoreCase("v"))angka=21;
            else if(abjad.equalsIgnoreCase("w"))angka=22;
            else if(abjad.equalsIgnoreCase("x"))angka=23;
            else if(abjad.equalsIgnoreCase("y"))angka=24;
            else if(abjad.equalsIgnoreCase("z"))angka=25;
            
        return angka;
    }
}
