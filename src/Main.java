import java.util.Random;
import java.util.Scanner;

public class Main {
    static void llenarLibresTablero(char[][]tablero, char relleno){
        for (int i = 0; i < tablero.length; i++) {
            for (int f = 0; f < tablero[i].length; f++) {
                tablero[i][f] = relleno;
            }
        }
    }
    static void imprimirTablero(char[][]tablero){
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero[i].length; j++) {
                System.out.print(tablero[i][j]+ " ");
            }
            System.out.println();
        }
    }
    static void anadirAlTablero(char[][]tablero, char relleno, int cantidad, char libre){
        Random rd = new Random();
        int ranFil;
        int ranCol;
        for (int i = 0; i < cantidad; i++) {
            do{
                ranFil = rd.nextInt(tablero.length);
                ranCol = rd.nextInt(tablero[0].length);
            }while(tablero[ranFil][ranCol]!= libre);
           tablero[ranFil][ranCol] = relleno;
        }
    }
    static void movimiento(char[][]tablero, char item, int posX, int posY, int posAntX,
                           int posAntY, char jugador,int vias){
        tablero[posY][posX] = jugador;
        tablero[posAntY][posAntX] = item;
        vidas++;
    }
    public static void main(String[] args) {
        int MAX_FIL_YODA = 10;
        int MAX_COL_YODA = 10;
        int MAX_FIL_DM = 10;
        int MAX_COL_DM = 10;
        char YODA = 'Y';
        char LIBRE = 'L';
        char DM = 'D';
        boolean yodaActive = true;
        boolean dmActive = false;
        // NO VOY A UTILIZAR FUNCIONES/PROCEDIMIENTOS
        char[][] tableroYoda = new char[MAX_FIL_YODA][MAX_COL_YODA];
        char[][] tableroDM = new char[MAX_FIL_DM][MAX_COL_DM];
        // tableroYoda
        char[][] tableroACTIVO;
        /*if((yodaActive==true) && (dmActive==false)){
            tableroACTIVO = tableroYoda;
        }else{
            tableroACTIVO = tableroDM;
        }*/
        if(yodaActive==true){

            tableroACTIVO = tableroYoda;
        }else{

            tableroACTIVO = tableroDM;
        }


        llenarLibresTablero(tableroACTIVO, LIBRE);
        imprimirTablero(tableroACTIVO);

        // 1 YODA
        Random aleat = new Random();
        int filaAl =-1;
        int colAl= -1;
        int filaOficialActivo;
        int columnaOficialActivo;

        for (int i = 0; i < 1; i++) {
            do{
                filaAl = aleat.nextInt(MAX_FIL_YODA);
                colAl = aleat.nextInt(MAX_COL_YODA);
            }while(tableroYoda[filaAl][colAl]!=LIBRE);
            tableroYoda[filaAl][colAl] = YODA;
        }
        // OFICIALIZO LAS POSICIONES DE MI YODA
        filaOficialActivo = filaAl;
        columnaOficialActivo = colAl;
        // FIN
        char MURO = 'M';
        anadirAlTablero(tableroACTIVO, MURO, 10, LIBRE);

        char VADER = 'V';
        anadirAlTablero(tableroACTIVO, VADER, 7, LIBRE);


        for (int i = 0; i < 20; i++) {
            System.out.println();
        }

        char POCIMA = 'P';
        anadirAlTablero(tableroACTIVO, POCIMA, 5, LIBRE);

        imprimirTablero(tableroACTIVO);
        System.out.println();

        imprimirTablero(tableroYoda);


        // EMPIEZA EL JUEGO !!!
        int vidas = 10;
        Scanner lector = new Scanner(System.in);
        do {
            System.out.println("EMPIEZA EL JUEGO!!");
            System.out.println("Te mueves en WASD");
            System.out.println("Dime una letra de movimiento !!");
            char mov = lector.next().toLowerCase().charAt(0);
            int filaAnteriorActivo = filaOficialActivo;
            int colAnteriorActivo = columnaOficialActivo;
            switch (mov){ // W, A, S o D
                case 'w':
                    filaOficialActivo -= 1;
                    break;
                case 'a':
                    columnaOficialActivo -= 1;
                    break;
                case 's':
                    filaOficialActivo += 1;
                    break;
                case 'd':
                    columnaOficialActivo += 1;
                    break;
            }

            // Y SI YODA SE SALE DEL TABLERO ??????????????????
            if(
                    filaOficialActivo < 0 ||
                            filaOficialActivo > 9 ||
                            columnaOficialActivo < 0 ||
                            columnaOficialActivo > 9
            ){
                System.out.println("EHHHHHHHH!!!!!!!!!!!!");
                System.out.println("LÍMITE DE TABLERO!!!!!!");
                filaOficialActivo = filaAnteriorActivo;
                columnaOficialActivo = colAnteriorActivo;

            }else{
                if(tableroACTIVO[filaOficialActivo][columnaOficialActivo] == MURO){
                    System.out.println("NO PUEDES PASAR!!!!!");
                    filaOficialActivo = filaAnteriorActivo;
                    columnaOficialActivo = colAnteriorActivo;
                }else if(tableroACTIVO[filaOficialActivo][columnaOficialActivo] == POCIMA){
                    tableroACTIVO[filaOficialActivo][columnaOficialActivo] = YODA;
                    tableroACTIVO[filaAnteriorActivo][colAnteriorActivo] = LIBRE;
                    vidas++;
                }else if(tableroACTIVO[filaOficialActivo][columnaOficialActivo] == VADER){
                    tableroACTIVO[filaOficialActivo][columnaOficialActivo] = YODA;
                    tableroACTIVO[filaAnteriorActivo][colAnteriorActivo] = LIBRE;
                    vidas--;
                }else if(tableroACTIVO[filaOficialActivo][columnaOficialActivo] == LIBRE){
                    tableroACTIVO[filaOficialActivo][columnaOficialActivo] = YODA;
                    tableroACTIVO[filaAnteriorActivo][colAnteriorActivo] = LIBRE;
                }
            }
            imprimirTablero(tableroACTIVO);

        }while(vidas>0);










    }
}