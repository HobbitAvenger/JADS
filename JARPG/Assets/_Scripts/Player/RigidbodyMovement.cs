using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class RigidbodyMovement : MonoBehaviour, IMovement
{
    
    private Vector3 velocityVector;
    private Rigidbody2D rigidBody2D;

    [SerializeField] private float moveSpeed;


    private void Awake(){
        rigidBody2D = GetComponent<Rigidbody2D>();
    }

    public void SetVelocity(Vector3 velocityVector){
        this.velocityVector = velocityVector;
    }

    private void FixedUpdate(){
        rigidBody2D.velocity = velocityVector * moveSpeed;
    }



}
