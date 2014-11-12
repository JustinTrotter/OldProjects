//gcc pi.c -w -lpthread -lm

#include <stdio.h>
#include <sys/types.h>
#include <pthread.h>
#include <stdlib.h>
#include <time.h>
#include <math.h>

//Global Variables
double total = 60000000;
double inCircle = 0;
pthread_mutex_t mutexInCircle;

void *plotPoints()
{
	int i;
	for (i = 0; i < total/4; i++)
	{
		//Get Random X
		double rangeX = (1 -(-1));
		double divX = RAND_MAX / rangeX;
		double randX = fabs(-1 + (rand() / divX));
		
		//Get Random Y
		double rangeY = (1 - (-1));
		double divY = RAND_MAX / rangeY;
		double randY = fabs(-1 + (rand() / divY));
		
		//Get Distance from Center Point
		double distance = (sqrt(randX * randX + randY * randY));

		//If within the circle
		if (distance <= 1){
			//lock -- increment -- unlock
			pthread_mutex_lock(&mutexInCircle);
			inCircle++;
			pthread_mutex_unlock(&mutexInCircle);
		}
	}
	
}

void main (int argc, char *argv[], char *envp[])
{
	//Generate Random Seed Based on Clock Millisecond
	struct timeval time; 
     	gettimeofday(&time,NULL);
	srand((time.tv_sec * 1000) + (time.tv_usec / 1000));

	//Initialize threads
	pthread_t thread1;	
	pthread_t thread2;
	pthread_t thread3;
	pthread_t thread4;

	//Build threads and start coroutines
	pthread_create(&thread1, NULL, plotPoints, (void *) NULL);
	pthread_join(thread1, NULL);	
	pthread_create(&thread2, NULL, plotPoints, (void *) NULL);
        pthread_join(thread2, NULL);
	pthread_create(&thread3, NULL, plotPoints, (void *) NULL);
        pthread_join(thread3, NULL);
	pthread_create(&thread4, NULL, plotPoints, (void *) NULL);
        pthread_join(thread4, NULL);
	
	//Print results	
	double result = 4 * inCircle / total;
	printf("Pi = %f\n", result);

	//Exit cleanly
	exit(NULL);
}
