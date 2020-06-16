using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class PbJsonData 
{
    public class Player
    {
        public int id;
        public int seat;
        public string name;
    }

    public class Players
    {
        public Player[] players;
    }
}
