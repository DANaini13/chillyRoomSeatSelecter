using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using DG.Tweening;
using UnityEngine.UI;
using LitJson;

public class SeatEventControl : MonoBehaviour
{
    public GameObject canvasObj;
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
            errorNum = (int)errorInfo["errorCode"];

            if (errorNum == 0)
            {
                SeatManager.Instance.curId = (int)jsonData["userId"];
                canvasObj.SetActive(false);
                sceneObj.SetActive(true);
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
}
