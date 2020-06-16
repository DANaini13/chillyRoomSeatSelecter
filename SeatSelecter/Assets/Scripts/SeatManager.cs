using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class SeatManager : BaseSingleton<SeatManager>
{
    public Transform seatParent;
    public List<SeatInfo> seatInfos;
    public GameObject playerObj;
    // Start is called before the first frame update

    public string curName ;

    private void Awake()
    {
        seatInfos = new List<SeatInfo>();
    }
    void Start()
    {
        for (int i = 0; i < seatParent.childCount; i++)
        {
            SeatInfo seatInfo = seatParent.GetChild(i).GetComponent<SeatInfo>();
            if (seatInfo != null)
            {
                seatInfos.Add(seatInfo);
            }


        }
    }


    public void RefreshPlayer()
    {
        while (seatInfos.Count ==0)
        {
            return;
        }
        Debug.Log("刷新");
        for (int i = 0; i < seatInfos.Count; i++)
        {
            if (!seatInfos[i].isNull)
            {
                seatInfos[i].playerObj = Instantiate(playerObj, seatInfos[i].transform);
                seatInfos[i].text.text = curName;
            }
            else
            {
                if (seatInfos[i].playerObj != null)
                {
                    Destroy(seatInfos[i].playerObj);
                }
                seatInfos[i].text.text = "空";
            }
        }
    }

}
