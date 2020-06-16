using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using DG.Tweening;
using UnityEngine.UI;

public class SeatEventControl : MonoBehaviour
{
    public GameObject canvasObj;
    public GameObject sceneObj;

    public Text errorText;

    public InputField InputField;

    public void CheckName()
    {
        //检查姓名是否通过
        if (InputField.text =="xjj")
        {
            SeatManager.Instance.curName = "xjj";
            canvasObj.SetActive(false);
            sceneObj.SetActive(true);
        }
        else
        {
            errorText.DOKill();
            errorText.color = new Color32(255, 37, 37, 255);

            errorText.DOColor(new Color32(255, 37, 37, 0), 1).SetDelay(1);
        }
    }
}
