using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using LitJson;

public class SeatSelectControl : MonoBehaviour
{
    public GameObject arrowObj;

    public GameObject curArrowObj;

    public GameObject lastArrowObj;



    // Update is called once per frame
    void Update()
    {
        if (Input.GetMouseButtonDown(0))
        {

            RaycastHit hit;
            Ray ray = Camera.main.ScreenPointToRay(Input.mousePosition);

            if (Physics.Raycast(ray, out hit))
            {
                if (hit.collider.tag == "seat")
                {
                    SeatInfo seatInfo = hit.collider.GetComponent<SeatInfo>();

                    SeatManager.Instance.webServices.selectSeat(SeatManager.Instance.curId, seatInfo.seatId, (string str) => {

                        Debug.Log(str);
                     //   JsonData jsonData = JsonMapper.ToObject(str);
                       // Debug.Log((int)jsonData["errorCode"]);
                        //if ((int)jsonData["errorCode"] == 5)
                        //{
                        //    Debug.Log("座位已经有人");
                        //}
                        //else
                        //{
                        //    curArrowObj = Instantiate(arrowObj, hit.collider.transform);
                        //    curArrowObj.transform.localPosition = new Vector3(0, 2, 0);
                        //    if (lastArrowObj != null)
                        //    {
                        //        Destroy(lastArrowObj);
                        //    }
                        //    lastArrowObj = curArrowObj;
                        //}
                    });
                    //设置坐下

                    //设置姓名
                    //设置上次的为空
  
                    //刷新
                 //   SeatManager.Instance.RefreshPlayer();


               

                }

            }

        }
        if (Input.GetMouseButtonDown(1))
        {
            Camera.main.GetComponent<FreeView>().enabled = true;
        }
    }
}
