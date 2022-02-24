using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class InputScript : MonoBehaviour
{

    private void Update(){

        float moveX = 0f;
        float moveY = 0f;

        Vector3 moveVector = new Vector3(moveX,moveY).normalized;
        if(Input.GetKey(KeyCode.W)) moveVector = new Vector3(0,+1f);
        if(Input.GetKey(KeyCode.S)) moveVector = new Vector3(0,-1f);
        if(Input.GetKey(KeyCode.A)) moveVector = new Vector3(-1f,0);
        if(Input.GetKey(KeyCode.D)) moveVector = new Vector3(+1f,0);

        
        GetComponent<IMovement>().SetVelocity(moveVector);
        


        
    }
}
