#include <stdio.h>
#include <sys/types.h>
#include <pthread.h>
#include <stdlib.h>

//Global variables
int average = 0;
int minimum = 0;
int maximum = 0;


//Data Structure
struct input {
	int count;
	int* array;

};

//Threaded function
//Sets average global variable
//Input from argv[]
void *getAverage(void *tid)
{
	//Parse data structure from arguement
	struct input *data;
	data = (struct input *)tid;
	int *array = data->array;
	int count = data->count;
        int result = 0;
	int i;
	
	//Loop through argv and get average
        for (i = 0; i < count; i++)
        {
                result = result + (array[i]);
        }

	//Store result globally
        average = result / (count);

	//Exit cleanly
	pthread_exit(NULL);
}

//Threaded function
//Sets minimum global variable
//Input from argv[]
void *getMinimum(void *tid)
{
	//Parse data structure from arguement
	struct input *data;
	data = (struct input *)tid;
	int *array = data->array;
	int count = data->count;
        int result = array[1];
	int i;

	//Loop through argv and get min
        for (i = 1; i < count; i++)
        {
                if (result > array[i])
                {
                        result = array[i];
                }
        }
	
	//Store result globally
        minimum = result;

	//Exit cleanly
	pthread_exit(NULL);
}

//Threaded function
//Sets minimum global variable
//Input from argv[]
void *getMaximum(void *tid)
{
	//Parse data structure from argument
	struct input *data;
	data = (struct input *)tid;
	int *array = data->array;
	int count = data->count;
        int result = array[1];
	int i;

	//Loop through argv and get max
        for (i = 1; i < count; i++)
        {
                if (result < array[i])
                {
                        result = array[i];
                }
        }

	//Store result globally
	maximum = result;

	//Exit cleanly
       	pthread_exit(NULL);
}

void main(int argc, char *argv[], char *envp[])
{
	//Build Data Structure to pass into threaded functions
	struct input data;
	int array[argc-1];
	int j;
	for (j = 0; j < argc - 1; j++)
	{
		array[j] = atoi(argv[j+1]);
	}
	data.array = array;
	data.count = argc - 1;
	
	//Initialize threads
	pthread_t thread1;
	pthread_t thread2;
	pthread_t thread3;
	
	//Build threads and start coroutines
	pthread_create(&thread1, NULL, getAverage, (void *) &data);
	pthread_join(thread1, NULL);
	pthread_create(&thread2, NULL, getMinimum, (void *) &data);
	pthread_join(thread2, NULL);
	pthread_create(&thread3, NULL, getMaximum, (void *) &data);
	pthread_join(thread3, NULL);

	//Print results
	printf("Average: %d\n",average);
	printf("Minimum: %d\n",minimum);
	printf("Maximum: %d\n",maximum);	

	//Exit cleanly
	exit(NULL);
}
