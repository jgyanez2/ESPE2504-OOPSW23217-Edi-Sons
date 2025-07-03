/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espe.wargame.model;

/**
 *
 * @author Jose Yanez
 */
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class GameTimer {
    private Timer timer;
    private final long duration; // Duración en ms
    private long remainingTime; // Tiempo restante para pausa/reanudar
    private long endTime; // Momento en que debe terminar el timer
    private boolean isRunning;

    public GameTimer(long duration) {
        this.duration = duration;
        this.remainingTime = duration;
        this.timer = new Timer();
        this.isRunning = false;
    }

    public void startTimer() {
        if (isRunning) {
            System.out.println("El temporizador ya esta corriendo.");
            return;
        }

        endTime = System.currentTimeMillis() + remainingTime;

        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                System.out.println("¡El tiempo ha terminado!");
                isRunning = false;
                remainingTime = 0;
            }
        };

        timer = new Timer();
        timer.schedule(task, remainingTime);
        isRunning = true;
        System.out.println("Temporizador iniciado. Duracion: " + remainingTime / 1000 + " segundos.");
    }

    public void pauseTimer() {
        if (!isRunning) {
            System.out.println("El temporizador no esta corriendo.");
            return;
        }
        timer.cancel();
        remainingTime = endTime - System.currentTimeMillis();
        isRunning = false;
        System.out.println("Temporizador pausado. Tiempo restante: " + remainingTime / 1000 + " segundos.");
    }

    public void resumeTimer() {
        if (isRunning) {
            System.out.println("El temporizador ya esta corriendo.");
            return;
        }
        if (remainingTime <= 0) {
            System.out.println("El temporizador ya ha finalizado. Por favor, reinícielo.");
            return;
        }
        startTimer();
    }
    
    public void remainingTime () {
        System.out.println("Tiempo restante: " + remainingTime / 1000 + " segundos.");
    }

    public void resetTimer() {
        timer.cancel();
        remainingTime = duration;
        isRunning = false;
        System.out.println("Temporizador reiniciado.");
    }

    public void mostrarMenuTemporizador(Scanner scanner) {
        while (true) {
            System.out.println("\n=== Menu del Temporizador ===");
            System.out.println("1. Iniciar temporizador");
            System.out.println("2. Pausar temporizador");
            System.out.println("3. Reanudar temporizador");
            System.out.println("4. Reiniciar temporizador");
            System.out.println("5. TiempoRestante");
            System.out.println("6. Volver al menu principal");
            System.out.print("Seleccione una opcion: ");
            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    startTimer();
                    break;
                case 2:
                    pauseTimer();
                    break;
                case 3:
                    resumeTimer();
                    break;
                case 4:
                    resetTimer();
                    break;
                case 5 :
                    remainingTime ();
                    break;
                case 6:
                    return;
                default:
                    System.out.println("Opcion invalida. Intente nuevamente.");
            }
        }
    }
}