using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class SeatSelectControl : MonoBehaviour
{
    public GameObject arrowObj;

    public GameObject curArrowObj;

    public GameObject lastArrowObj;



    void Start()
    {
        
    }

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
                    while (!seatInfo.isNull)
                    {
                        return;
                    }
                    //设置坐下
                    seatInfo.isNull = false;
                    //设置姓名
                    //设置上次的为空
  
                    //刷新
                    SeatManager.Instance.RefreshPlayer();


                    curArrowObj = Instantiate(arrowObj, hit.collider.transform);
                    curArrowObj.transform.localPosition = new Vector3(0, 2, 0);
                    if (lastArrowObj != null)
                    {
                        Destroy(lastArrowObj);
                    }
                    lastArrowObj = curArrowObj;

                }

            }

        }
        //if (Input.GetMouseButtonDown(1))
        //{
        //    Camera.main.GetComponent<FreeView>().enabled = true;
        //}
    }
}
