using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using DG.Tweening;
using UnityEngine.UI;
using LitJson;

public class SeatEventControl : MonoBehaviour
{
    public GameObject loginObj;
    public GameObject mainMenuObj;
    public GameObject sceneObj;

    public Text errorText;

    public InputField InputField;

    public void CheckName()
    {
        int errorNum = -1;
        SeatManager.Instance.webServices.setName(InputField.text, (string str) => {
            Debug.Log(str);
            JsonData jsonData = JsonMapper.ToObject(str);
            JsonData errorInfo = jsonData["errorInfo"];
            JsonData seatUnitInfo = jsonData["seatUnit"];
            errorNum = (int)errorInfo["errorCode"];

            if (errorNum == 0)
            {
                SeatManager.Instance.curId = (int)seatUnitInfo["id"];
                loginObj.SetActive(false);
                mainMenuObj.SetActive(true);
                sceneObj.SetActive(true);
                //设置时间
                SeatManager.Instance.webServices.grabTimeStamp((long f) => {
                    Debug.Log("时间:" + f);
                    SeatManager.Instance.curTime = f;

                });
                SeatManager.Instance.CheckStartTime((int)seatUnitInfo["start_time"], (int)seatUnitInfo["end_time"]);
                SeatManager.Instance.startTime = (int)seatUnitInfo["start_time"];
                SeatManager.Instance.endTime = (int)seatUnitInfo["end_time"];
            }
            else
            {
                errorText.DOKill();
                errorText.color = new Color32(255, 37, 37, 255);

                errorText.DOColor(new Color32(255, 37, 37, 0), 1).SetDelay(1);
            }
        });
        //检查姓名是否通过

    }



    public void ExitGame()
    {
        Application.Quit();
    }
}
