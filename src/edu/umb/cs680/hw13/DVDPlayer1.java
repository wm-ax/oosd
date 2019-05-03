// package edu.umb.cs680.hw13;

// public interface DVDPlayer {
    
//     private State state;
//     private static DVDPlayer instance;

//     private DVDPlayer() {
//         System.out.println("initializing state in constructor, to...");
//         System.out.println(state);
//     }

//     public static DVDPlayer getInstance() {
//         if (instance==null) {
//             instance = new DVDPlayer();
//             state = DrawerClosedNotPlaying.getInstance();
//         }
//         return instance;
//     }

//     public void changeState(State newState) {
//         System.out.println("changeState called");
//         System.out.println(state);
//         state = newState;
//         System.out.println(state);
//     }

//     public State getState() {
//         return state;
//     }

//     public void openCloseButtonPushed() {
//         state.openCloseButtonPushed();
//     }

//     public void playButtonPushed() {
//         state.playButtonPushed();
//     }

//     public void stopButtonPushed() {
//         state.stopButtonPushed();
//     }

//     public void open() {
//     }

//     public void close() {
//     }

//     public void play() {
//     }

//     public void stop() {
//     }

//     // public void lightDisplay() {
//         // System.out.println(state.getDisplay());
//     // }

//     public static void main(String[] args) {
//         DVDPlayer player = getInstance();
//         // player.lightDisplay();
//         player.play();
//         player.changeState(DrawerClosedPlaying.getInstance());
//         // player.lightDisplay();
//         player.play();
//     }
// }


