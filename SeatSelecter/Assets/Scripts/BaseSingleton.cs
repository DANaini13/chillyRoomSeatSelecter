using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class BaseSingleton<T> : MonoBehaviour where T : BaseSingleton<T>
{

    // Use this for initialization
    
    protected static T _instance = null;
    private static bool isApplicationStop = false;

    public static T Instance
    {
        get
        {
            if (isApplicationStop)
            {
                return null;
            }
            if ( _instance==null)
            {
                GameObject go = GameObject.Find("GameManager");

                if (go == null)
                {
                    go = new GameObject("GameManager");
                    DontDestroyOnLoad(go);
                }
                _instance = go.GetComponent<T>();

                if (_instance==null)
                {
                    _instance = go.AddComponent<T>();
                }

            }
            return _instance;
        }
    }
    private void OnApplicationQuit()
    {
        isApplicationStop = true;
    }

}
