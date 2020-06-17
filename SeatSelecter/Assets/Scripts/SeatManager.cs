using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using LitJson;

public class SeatManager : BaseSingleton<SeatManager>
{
    public Transform seatParent;
    public List<SeatInfo> seatInfos;
    public GameObject playerObj;

    public Transform seatModelParent;


    public GameObject seatTargetObj;

    public WebServices webServices;

    public int curId = -1 ;

    public List<int> seatIndexList;

    private void Awake()
    {
        seatInfos = new List<SeatInfo>();
        seatIndexList = new List<int>();
    }
    void Start()
    {
        for (int i = 0; i < seatModelParent.childCount; i++)
        {
            GameObject seatGo = Instantiate(seatTargetObj, seatParent);
            Vector3 pos = seatModelParent.GetChild(i).position;
            seatGo.transform.position = new Vector3(pos.x, seatGo.transform.position.y, pos.z);

        }

        for (int i = 0; i < seatParent.childCount; i++)
        {
            SeatInfo seatInfo = seatParent.GetChild(i).GetComponent<SeatInfo>();
            seatInfo.seatId = i;
            seatInfo.eulerAngleY = seatModelParent.GetChild(i).localEulerAngles.y;
            if (seatInfo != null)
            {
                seatInfos.Add(seatInfo);
            }


        }
        webServices.setSeatChangeListener((string str) =>
        {
       
            JsonData jsonData= JsonMapper.ToObject(str);

            RefreshPlayer(jsonData);
        });
    }


    public void RefreshPlayer(JsonData jsonData)
    {
        while (seatInfos.Count ==0)
        {
            return;
        }
        seatIndexList.Clear();
        foreach (JsonData playerInfo in jsonData)
        {
            int seatIndex = (int)playerInfo[1];
            string nameStr = (string)playerInfo[2];
            Debug.Log(seatInfos.Count);
            while (seatIndex+1 <= seatInfos.Count)
            {
                if (seatIndex != -1)
                {
                    if (seatInfos[seatIndex].playerObj == null)
                    {
                        seatInfos[seatIndex].playerObj = Instantiate(playerObj, seatInfos[seatIndex].transform);
                        seatInfos[seatIndex].playerObj.transform.localEulerAngles = new Vector3(0, seatInfos[seatIndex].eulerAngleY, 0);
                    }

                    seatInfos[seatIndex].text.text = nameStr;
                    seatInfos[seatIndex].text.color = new Color32(255,171,0,255);
                    seatIndexList.Add(seatIndex);
                }
                break;
            }
       
   
        }
        for (int i = 0; i < seatInfos.Count; i++)
        {
            if (!seatIndexList.Contains(i))
            {
                seatInfos[i].text.text = "";
               Destroy(seatInfos[i].playerObj);
            }
        }
    }

}
