using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.AI;

public class EnemyAITutorial : MonoBehaviour
{

    public NavMeshAgent agent;

    public Transform player;
    public LayerMask whatIsGround, whatIsPlayer;

    //Patrolling
    public Vector3 walkPoint;
    bool walkPointSet;
    public float WalkPointRange;


//Attacking
    public float timeBetweenAttacks;
    public bool alreadyAttacked;

//States
    public float sightRange, attackRange;
    public bool playerInSightRange, playerInAttackRange;




    private void Update(){
        playerInSightRange = Physics.CheckSphere(transform.position, sightRange);
        playerInAttackRange = Physics.CheckSphere(transform.position, attackRange);
        if(!playerInAttackRange && !playerInSightRange) Patrolling();
        if(playerInSightRange && !playerInAttackRange) ChasePlayer();
        if(playerInAttackRange && playerInSightRange) AttackPlayer();
    }

    private void Awake(){
        player = GameObject.Find("PlayerObj").transform;
        agent = GetComponent<NavMeshAgent>();
    }

    private void Patrolling(){
        if(!walkPointSet) SearchWalkPoint();
    }
    private void SearchWalkPoint(){
        
    }

    private void ChasePlayer(){

    }
    private void AttackPlayer(){

    }

}
