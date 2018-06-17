package Models;

import android.app.Activity;
import android.util.Log;

import mytechcorp.ir.assisstant.DBHandler;

public class Coding {

    Activity a;
    private DBHandler dbHandler;
    private int gameCode, addedNum, groupCode1,groupCode2;
    private String gameScore;

    public Coding(Activity c) {
        this.a = c;
        dbHandler = new DBHandler(c);
    }


    public boolean CheckCode(String Code, int Gameid){
        if (Code.isEmpty() || Code.length()!=7){
            return false;
        }
        InitCode(Code);
        if (CheckGame(Gameid)){
            if(CheckValidGroupCode(addedNum, groupCode1, groupCode2)){
                return true;
            }
            else return false;
        }
        else return false;
    }

    private void InitCode(String code){
        addedNum = Integer.parseInt(code.substring(0,1)); //Added Number
        groupCode1 = Integer.parseInt(code.substring(1,2)); //First Number of groupCode
        groupCode2 = Integer.parseInt(code.substring(2,3)); //Second Number of groupCode
        gameCode = Integer.parseInt(code.substring(3,5)); //Game ID
        gameScore = code.substring(5,7); //Game Score Based 6

    }

    private boolean CheckGame(int Gameid){
        if(gameCode == Gameid){
            return true;
        }
        else
            return false;
    }

    private boolean CheckValidGroupCode(int AddedNum,int GroupCode1 ,int GroupCode2){
        int a;
        String v1;
        if (GroupCode1 >= AddedNum){
            a = GroupCode1-AddedNum;
            v1 = String.valueOf(a) + String.valueOf(GroupCode2);
            if (v1.equals(dbHandler.GetGCode(1))){
                return true;
            }
            else
                return false;
        }
        else if (GroupCode1<AddedNum){
            //b = GroupCode2 - 1;
            a = 10 + GroupCode1 - AddedNum;
            v1 = String.valueOf(a) + String.valueOf(GroupCode2-1);
            if (v1.equals(dbHandler.GetGCode(1))){
                return true;
            }
            else
                return false;
        }
        else return false;
    }

    public String ConvertCodeToScore(){
        return Integer.toString(Integer.parseInt(gameScore, 6), 10);
    }

    public String GetMatchTime() {
        if (gameScore.equals("09"))
            return "15 دقیقه اول";
        else if (gameScore.equals("18"))
            return "15 دقیقه دوم";
        else if (gameScore.equals("27"))
            return "15 دقیقه سوم";
        else if (gameScore.equals("36"))
            return "15 دقیقه چهارم";
        else if (gameScore.equals("45"))
            return "15 دقیقه پنجم";
        else if (gameScore.equals("54"))
            return "15 دقیقه ششم";
        else if (gameScore.equals("63"))
            return "15 دقیقه هفتم";
        else if (gameScore.equals("72"))
            return "15 دقیقه هشتم";
        else if (gameScore.equals("81"))
            return "15 دقیقه نهم";
        else
            return "کد اشتباه است";
    }
}
