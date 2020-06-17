using System.Collections;
using UnityEngine;

public delegate void updateUserList(string userList);
public delegate void selectSeatCallback(string result);
public delegate void setNameCallback(string result);
public delegate void grabTimeCallback(long timeStamp);

delegate void postCallback(string result);
public class WebServices : MonoBehaviour
{

    /** following are APIs */
    public void setSeatChangeListener(updateUserList callback) => this.userListUpdateCallback = callback;

    [System.Obsolete]
    public void setName(string name, setNameCallback callback) 
    {
        WWWForm form = new WWWForm();
        form.AddField("name", name);
        StartCoroutine(SendPost(url + "checkName/", form, (string result) => {
            callback(result);
        }));
    }

    [System.Obsolete]
    public void selectSeat(int userId, int seat, selectSeatCallback callback)
    {
        WWWForm form = new WWWForm();
        form.AddField("userId", userId);
        form.AddField("seatId", seat);
        StartCoroutine(SendPost(url + "setSeat/", form, (string result) => {
            callback(result);
        }));
    }

    [System.Obsolete]
    public void grabUserList(updateUserList callback)
    {
        WWWForm form = new WWWForm();
        StartCoroutine(SendPost(url + "queryUsers", form, (string result) => {
            callback(result);
        }));
    }

    [System.Obsolete]
    public void grabTimeStamp(grabTimeCallback callback)
    {
        WWWForm form = new WWWForm();
        StartCoroutine(SendPost(url + "timestamp", form, (string result) => {
            long now = long.Parse(result);
            callback(now);
        }));
    }

    private const string url = "http://106.55.244.225:8080/seats/";

    [System.Obsolete]
    void Start() {
        StartCoroutine(refreshUserListFromWeb(0.1f));
    }
    private updateUserList userListUpdateCallback = null;

    [System.Obsolete]
    IEnumerator SendPost(string _url, WWWForm _wForm, postCallback callback)
    {
        WWW postData = new WWW(_url, _wForm);
        yield return postData;
        if (postData.error != null)
        {
            callback(postData.error);
        }
        else
        {
            callback(postData.text);
        }
    }

    [System.Obsolete]
    IEnumerator refreshUserListFromWeb(float waitDuration) 
    {
        while(true) {
            yield return new WaitForSecondsRealtime(waitDuration);
            if(userListUpdateCallback != null) 
            {
                WWWForm form = new WWWForm();
                StartCoroutine(SendPost(url + "queryUsers", form, (string result) => {
                    userListUpdateCallback(result);
                }));
            }
        }
    }

}
