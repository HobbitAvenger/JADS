using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class TransformMovement : MonoBehaviour, IMovement
{
    private Vector3 velocityVector;
    private Rigidbody2D rigidBody2D;

    [SerializeField] private float moveSpeed;



    public void SetVelocity(Vector3 velocityVector){
        this.velocityVector = velocityVector;
    }

    private void Update(){
        transform.position += velocityVector * moveSpeed * Time.deltaTime;
    }
}
