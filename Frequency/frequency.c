//Justin Trotter 10144892
//CSCI 423 Project Basic Part

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <math.h>
#include <time.h>
#include <pthread.h>

//Hashing Constants
#define FNV_PRIME_32_BIT 16777619 // = 2^24 + 2^8 + 0x93
#define OFFSET_BASIS_32_BIT 2166136261

//Other Constants
#define ARRAY_SIZE 10
#define MAX_STRING_SIZE 32
#define NUMBER_OF_THREADS 2

//Global Variables
int uniqueStrings[NUMBER_OF_THREADS];

//Define data structures
typedef struct dataArray dataArray;
typedef struct data data;
typedef struct list list;
typedef struct buffer buffer;
typedef struct threadArgs threadArgs;

struct threadArgs  {
	int threadID;
	data* map;
	buffer *file_buffer;
	int count;
};

struct data {
	char string[MAX_STRING_SIZE];
	int number;
	data* next;
};

struct list {
	char string[MAX_STRING_SIZE];
	list* next;
};

struct buffer {
	char line[BUFSIZ];
};

//Function Prototypes
int getFreq(data map[], char *token);
unsigned long long alphaHash(char *token);
unsigned long long hash(unsigned long long octet_of_data);
void buildList(data map[], list stringList[], int size);
void linkListBase(list map[], uint imod, char *token);
void linkList(char *token, list* ptr);
void linkMapBase (data map[], uint imod, char *token, int t);
void linkMap (char *token, data* ptr, int t);
void report(data map[], list* stringList, FILE* outfile, int t);
void wipe(list* stringList, int size);
list* sort(list* stringList, int index, int t);
list* insertSort(list* stringList, int t);
list* radixSort(list* stringList, int t);

//Functions
unsigned long long alphaHash(char *token)
{
	long long ret = 0;
	int i;
	for(i = 0; i < strlen(token); i++)
		ret += (token[i] - 'a' + 1) * pow(10,(strlen(token) - 1 - i) * 2);
	return ret;
}

void linkMapBase (data map[], uint imod, char *token, int t)
{
	if(!strcmp(map[imod].string, ""))
	{
		strcpy(map[imod].string, token);
		uniqueStrings[t]++;
		map[imod].number = 1;
		printf("String: %s, Number = %d\n", token, map[imod].number);
	}
	else if (!strcmp(map[imod].string, token))
	{
		printf("String: %s, Number = %d\n", token, map[imod].number);
		map[imod].number++;
	}
	else
	{
		if (map[imod].next == NULL)
		{
			data* temp;
			temp = (data*)malloc(sizeof(data));
			strcpy(temp->string, token);
			uniqueStrings[t]++;
			temp->number = 1;
			printf("String: %s, Number = %d\n", token, temp->number);
			map[imod].next = temp;
		}
		else
			linkMap(token, map[imod].next, t);
	}
	return;
}

void linkMap (char *token, data* ptr, int t)
{
	if (!strcmp(ptr->string, token))
	{
		ptr->number++;
		printf("String: %s, Number = %d\n", token, ptr->number);
	}
	else
	{
		if (ptr->next == NULL)
		{
			data* temp;
			temp = (data*)malloc(sizeof(data));
			strcpy(temp->string, token);
			uniqueStrings[t]++;
			temp->number = 1;
			printf("String: %s, Number = %d\n", token, temp->number);
			ptr->next = temp;
		}
		else
			linkMap(token, ptr->next, t);
	}
	return;
}

int getFreq(data map[], char *token)
{
	int i, ret;
	unsigned long long itoken = alphaHash(token);
	unsigned long long htoken = hash(itoken);
	uint imod = htoken % ARRAY_SIZE;

	if (!strcmp(map[imod].string, token))
		ret = map[imod].number;
	else
	{
		data* temp;
		//if (map[imod].next != NULL)
		temp = map[imod].next;
		//else
			//return 0;
		while(temp->string != NULL && strcmp(temp->string, token))
		{
			temp = temp->next;
		}
		//printf("String: %s, number = %d\n",token, temp->number);
		ret = temp->number;
	}
	return ret;
}

void report(data map[], list* stringList, FILE* outfile, int t)
{
	printf("%d\n",uniqueStrings[t]);
	int j;
		for(j = 0; j < uniqueStrings[t]; j++)
			{
			//printf("%d: %s:\n",j,stringList[j].string);
			printf("%d: %s: %d\n",j,stringList[j].string, getFreq(map,stringList[j].string));
			//char* text = "%d: %s: %d\n",j,stringList[j].string, getFreq(map,stringList[j].string);
			fprintf(outfile, "%d: %s: %d\n",j,stringList[j].string, getFreq(map,stringList[j].string));
			//fprintf(outfile, "%d: %s:\n",j,stringList[j].string);
			}
}

void reportFinal(data map[], FILE* outfile, int t)
{
	printf("%d\n",uniqueStrings[t]);
	int j;
		for(j = 0; j < uniqueStrings[t]; j++)
			{
			//printf("%d: %s:\n",j,map[j].string);
			printf("%d: %s: %d\n",j,map[j].string, map[j].number);
			//char* text = "%d: %s: %d\n",j,stringList[j].string, getFreq(map,stringList[j].string);
			fprintf(outfile, "%d: %s: %d\n",j,map[j].string, map[j].number);
			//fprintf(outfile, "%d: %s:\n",j,stringList[j].string);
			}
}

void wipe(list* stringList, int size)
{
	int i, j;
		for(j = 0; j < size; j++)
		{
			for(i = 0; i < MAX_STRING_SIZE; i++)
			{
				stringList[j].string[i] = '\0';
			}
		}
}

void linkListBase(list map[], uint imod, char *token)
{
	if(!strcmp(map[imod].string, ""))
	{
		strcpy(map[imod].string, token);
		map[imod].next = NULL;
	}
	else if (!strcmp(map[imod].string, token))
	{
		//do nothing
	}
	else
	{
		if (map[imod].next == NULL)
		{
			list* temp;
			temp = (list*)malloc(sizeof(list));
			strcpy(temp->string, token);
			map[imod].next = temp;
		}
		else
			linkList(token, map[imod].next);
	}
	return;
}

void linkList(char *token, list* ptr)
{
	if (!strcmp(ptr->string, token))
	{
		//do nothing
	}
	else
	{
		if (ptr->next == NULL)
		{
			list* temp;
			temp = (list*)malloc(sizeof(list));
			strcpy(temp->string, token);
			ptr->next = temp;
		}
		else
			linkList(token, ptr->next);
	}
	return;
}

list* sort(list* stringList, int index, int t)
{
	list *tempList = malloc(uniqueStrings[t] * sizeof *tempList);
	int i, j;
	for(i = index; i > index - 1; i--)
	{
		list alphaList[27];
		wipe(alphaList,27);
		for(j = 0; j < uniqueStrings[t]; j++)
		{

			if(stringList[j].string[i-1] == '\0')
			{
				linkListBase(alphaList, 0, stringList[j].string);
			}
			else
			{
				linkListBase(alphaList, stringList[j].string[i-1] - 'a' + 1, stringList[j].string);
			}
		}
		copyList(alphaList, tempList, 27);
	}
	return tempList;
}

list* insertSort(list* stringList, int t)
{
	list temp;
	int c, d;
	int n = uniqueStrings[t];
	for (c = 1 ; c <= n - 1; c++) {
	    d = c;
	    while ( d > 0 && strcmp(stringList[d].string, stringList[d-1].string) < 0)
	    {
	      temp          = stringList[d];
	      stringList[d]   = stringList[d-1];
	      stringList[d-1] = temp;
	      d--;
	    }
	  }
	return stringList;
}

list* radixSort(list* stringList, int t)
{
	printf("RADIXSORT STARTED\n");
	list* sortedList;
	sortedList = sort(stringList, 4, t);
	int i;
	for(i = 3; i > 0; i--)
	{
		sortedList = sort(sortedList, i, t);
	}
	sortedList = insertSort(sortedList, t);
	printf("RADIXSORT FINISHED\n");
	return sortedList;
}

//Simple FNV-1 hashing algorithm
//Argument: value to be hashed
//Returns: extremely large hashed number;
unsigned long long hash(unsigned long long octet_of_data)
{
	unsigned long long hash = OFFSET_BASIS_32_BIT;
	hash = hash * FNV_PRIME_32_BIT;
	hash = hash ^ octet_of_data;
	return hash;
}

void copyList(list map[], list stringList[], int size)
{
	int i;
	int j = 0;
	for(i = 0; i < size; i++)
	{
		if (strcmp(map[i].string, ""))
		{
			strcpy(stringList[j++].string, map[i].string);
			list* temp;
			temp = map[i].next;
			while(temp != NULL)
			{
				strcpy(stringList[j++].string, temp->string);
				temp = temp->next;
			}
		}
	}
	return;
}

void buildList(data* map, list stringList[], int size)
{
	printf("BUILD LIST STARTED\n");
	int i;
	int j = 0;
	for(i = 0; i < size; i++)
	{
		if (strcmp(map[i].string, ""))
		{
			strcpy(stringList[j++].string, map[i].string);
			data* temp;
			temp = map[i].next;
			while(temp != NULL)
			{
				strcpy(stringList[j++].string, temp->string);
				temp = temp->next;
			}
		}

	}
	printf("BUILD LIST FINISHED\n");
	return;
}

list* mergeLists(list* listA, list* listB, data mapA[], data mapB[])
{
	data *mergedList = malloc(sizeof *mergedList);
	list tempA = listA[0];
	list tempB = listB[0];

	printf("LIST A FIRST ELEMENT: %s\n", tempA.string);
	printf("LIST B FIRST ELEMENT: %s\n", tempB.string);
	int i = 0;
	int j = 0;
	int c = 0;
	//printf("i = %d, j = %d", i, j);
	while(i < uniqueStrings[0] && j < uniqueStrings[1])
	{
		//printf("i = %d, j = %d", i, j);
		if(!strcmp(listA[i].string,listB[i].string))
		{
			c++;
			mergedList = realloc(mergedList, c * sizeof *mergedList);
			strcpy(mergedList[c-1].string,listA[i].string);
			mergedList[c-1].number = getFreq(mapA, listA[i].string) + getFreq(mapB, listB[i].string);
			i++;
			j++;
		}
		else if (strcmp(listA[i].string,listB[i].string) < 0)
		{
			c++;
			mergedList = realloc(mergedList, c * sizeof *mergedList);
			strcpy(mergedList[c-1].string,listA[i].string);
			mergedList[c-1].number = getFreq(mapA, listA[i].string);
			i++;
		}
		else
		{
			c++;
			mergedList = realloc(mergedList, c * sizeof *mergedList);
			strcpy(mergedList[c-1].string,listB[j].string);
			mergedList[c-1].number = getFreq(mapB, listB[i].string);
			j++;
		}
	}
	while(i < uniqueStrings[0])
	{
		c++;
		mergedList = realloc(mergedList, c * sizeof *mergedList);
		strcpy(mergedList[c-1].string,listA[i].string);
		mergedList[c-1].number = getFreq(mapA, listA[i].string);
		i++;
	}

	while(j < uniqueStrings[1])
	{
		c++;
		mergedList = realloc(mergedList, c * sizeof *mergedList);
		strcpy(mergedList[c-1].string,listB[i].string);
		mergedList[c-1].number = getFreq(mapB, listB[i].string);
		j++;
	}
	uniqueStrings[2] = c;
	return mergedList;
}

void *buildHashMap(void *context)
{
	printf("This does stuff\n");
	threadArgs *tArgs = context;
	int count = tArgs->count;
	buffer *file_buffer = tArgs->file_buffer;
	data* map = tArgs->map;
	int *threadID = tArgs->threadID;

	int j;
	char *token;
	const char delim[60] = "\n\t\\ \"\'¯~ö1234567890!#$%&()*+,./:;—-<=“”’>?@[]^_`{|}""“-";
	for(j = threadID; j < count; j=j+NUMBER_OF_THREADS)
	{
		token = strtok(file_buffer[j].line, delim);
		while(token != NULL)
		{
			printf("TOKEN: %s\n",token);
			int i;
			for (i = 0; token[i] != '\0'; i++)
				token[i] = tolower(token[i]);

			unsigned long long itoken = alphaHash(token);
			unsigned long long htoken = hash(itoken);
			uint imod = htoken % ARRAY_SIZE;
			linkMapBase(map,imod,token, threadID);
			token = strtok(NULL, delim);
		}
	}
	pthread_exit(NULL);
}

//Main
int main()
{
	//Generate Random Seed Based on Clock Millisecond
	struct timeval time;
	gettimeofday(&time,NULL);
	srand((time.tv_sec * 1000) + (time.tv_usec / 1000));

	//Initialize Global Variables;
	int i;
	for (i = 0; i < NUMBER_OF_THREADS; i++)
		uniqueStrings[i] = 0;

	//Initialize Local Variables;
	uint line_number = 0;
	data **map = malloc(NUMBER_OF_THREADS * sizeof **map);
	for(i = 0; i < NUMBER_OF_THREADS; i++)
	{
		map[i] = malloc(ARRAY_SIZE * sizeof **map);
	}
	buffer *file_buffer = malloc(1000 * sizeof *file_buffer);
	threadArgs *tArgs = malloc(NUMBER_OF_THREADS * sizeof *tArgs);

	//Open File
	char *inname = "input.txt";
	FILE *infile;
	char line_buffer[BUFSIZ];

	//Check if Opened
	infile = fopen(inname, "r");
	if(!infile){
		printf("Couldn't open file %s for reading.\n", inname);
		return 0;
	}
	printf("Opened file %s for reading.\n", inname);


	//Start Building Hashmap
	printf("BUILD HASHMAP STARTED\n");
	int count = 0;
	int index = 0;
	int offset = 0;
	int change = 100;
	int currentMax = 100;
	count = 0;
	while(fgets(line_buffer, sizeof(line_buffer),infile))
	{
		char *token;
		token = strtok(line_buffer, "\n");
		if(token != NULL)
		{
			if(file_buffer[count].line != NULL && count < 1000)
			{
				strcpy(file_buffer[count].line, token);
				printf("Index: %d Count: %d: %s\n",index, count,file_buffer[count].line);
				count++;
			}

			if (count == 1000)	//BUFFER FILLED, ACT UPON IT!
			{
				//Initialize Threads
				pthread_t threads[NUMBER_OF_THREADS];
				int status;
				int i;
				for(i = 0; i < NUMBER_OF_THREADS; i++)
				{
					tArgs[i].threadID = i;
					tArgs[i].map = map[i];
					tArgs[i].file_buffer = file_buffer;
					tArgs[i].count = count;
					status = pthread_create(&threads[i],NULL, buildHashMap, (void *) &tArgs[i]);
				}
				for(i = 0; i < NUMBER_OF_THREADS; i++)
				{
					status = pthread_join(threads[i],NULL);
				}

				count = 0;		//RESET BUFFER
			}
			index++;
		}
	}

	printf("BUILD HASHMAP FINISHED\n");

	FILE *outfile;
	outfile = fopen("output.txt", "w");
	if(outfile == NULL)
	{
		printf("Unable to open file.");
		return 0;
	}
	list **stringList = malloc(NUMBER_OF_THREADS * sizeof **stringList);
	list **sortedList = malloc(NUMBER_OF_THREADS * sizeof **sortedList);
	for(i = 0; i < NUMBER_OF_THREADS;i++)
	{
		stringList[i] = malloc(uniqueStrings[i] * sizeof **stringList);
		buildList(map[i], stringList[i], ARRAY_SIZE);
		sortedList[i] = radixSort(stringList[i], i);
	}

	list *mList = sortedList[0];
	data *mListMap = map[0];
	for(i = 1; i < NUMBER_OF_THREADS; i++)
	{
		mList = mergeLists(mList, sortedList[i], mListMap, map[i]);
	}
	int totalStrings = 0;
	for(i = 0; i < NUMBER_OF_THREADS; i++)
	{
		//free(map[i]);
		totalStrings += uniqueStrings[i];
	}
	uniqueStrings[0] = totalStrings;
	reportFinal(mList, outfile, 0);
	printf("Total number of unique strings = %d\n", totalStrings);
	free(stringList);
	free(sortedList);
	return 0;
}
