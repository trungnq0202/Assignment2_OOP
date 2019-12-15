package models;

import java.util.ArrayList;
import java.util.Collections;

//An object storing list of playerCard objects
public class PlayerCardList {
    private ArrayList<PlayerCard> playerCardList;

    public PlayerCardList(){
        playerCardList = new ArrayList<>(20);
        int imageNo = 1;
        for (int i = 0; i < 10; i++){
            for (int j = 0; j < 2; j ++) {
                PlayerCard playerCard = new PlayerCard(imageNo); //Create playerCard storing the image with imageNo number in the image file name
                playerCardList.add(playerCard);     //Add the card the the card list
            }
            imageNo++;
        }

        //Shuffle the list for the user to find and match each pair that has the same player image
        Collections.shuffle(playerCardList);
    }

    //Method for getting object playerCard in the list based on cardNo
    public PlayerCard getPlayerCard(int cardNo){
        return playerCardList.get(cardNo);
    }

    //Method for getting this list length
    public int getPlayerCardListSize(){
        return playerCardList.size();
    }

}
