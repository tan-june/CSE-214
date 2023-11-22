package cse214hw1;

import java.util.LinkedList;
public class HotPotato {
    public static DoublyLinkedList<Integer> playWithDoublyLinkedList(int numberOfPlayers, int lengthOfPass) {

        DoublyLinkedList<Integer> a = new DoublyLinkedList<>(1);
        DoublyLinkedList<Integer> newList = new DoublyLinkedList<>();
        int counter = 0;

        int playerno = 0;
        while (playerno < numberOfPlayers) {
            a.add(playerno + 1);
            playerno++;
        }

        while (a.size() > 0){
            if(lengthOfPass==0){
                return a;
            }
            else{
               for(int i=0; i<a.size(); i++){

                   if(counter == lengthOfPass){
                        newList.add(a.get(i));
                        a.remove(i);
                     }
                   else{
                       counter++;
                   }
               }
            }
        }

        return newList;
    }

    public static LinkedList<Integer> playWithLinkedList(int numberOfPlayers, int lengthOfPass) {

        LinkedList<Integer> a = new LinkedList<>();
        LinkedList<Integer> newList = new LinkedList<>();
        int playerno = 0;
        while (playerno < numberOfPlayers){
            a.add(playerno+1);
            playerno++;
        }
        int counter = 0;


        while (a.size() > 0){
            if(lengthOfPass==0){
                return a;
            }
            else{
                for(int i=0; i<a.size(); i++){

                    if(counter == lengthOfPass){
                        newList.add(a.get(i));
                        a.remove(i);
                    }
                    else{
                        counter++;
                    }
                }
            }
        }
    return newList;

    }


    public static void main(String... args) {
// in both methods, the list is the order in which the players are eliminated
// the last player (i.e., the last element in the returned list) is the winner
        System.out.println(playWithDoublyLinkedList(5, 0)); // expected output: [1, 2, 3, 4, 5]
        System.out.println(playWithLinkedList(5, 1)); // expected output: [2, 4, 1, 5, 3]
    }
}