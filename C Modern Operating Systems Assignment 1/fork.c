#include <stdio.h>
#include <sys/types.h>

void ChildProcess(int);
void ParentProcess(void);

void main(int argc, const char *argv[], char *envp[])
{
	int *input;
	input = atoi(argv[1]);

	pid_t pid;	
	pid = fork();

	if (pid == 0)
	{
		ChildProcess(input);
	}
	else if (pid < 0)
	{
		printf("Error! Child Process Failed to Start\n");
	}
	else
	{
		int returnStatus;
		waitpid(pid, &returnStatus, 0);
		if (returnStatus == 0)
		{
			printf("Child Process Terminated Successfully\n");
			ParentProcess();
		}
		else
			printf("Error! Child Process Failed to Terminate\n");
	}
}

void ChildProcess(int i)
{
	printf("Child Process Starting\n");
	int n = i;
	printf("%d ",n);
	while (n != 1) 
	{
		if (n < 1)
		{
			printf("\nError! Non-Positive Integer Detected\n");
			exit(0);
		}
		else if (n % 2 == 0) // even
		{
			n = n / 2;
			printf("%d ",n);
		}
		else
		{
			n = 3 * n + 1;
			printf("%d ",n);
		}
	}
	printf("\nChild Process Ending\n");
	exit(0);
}

void ParentProcess()
{
	printf("Parent Process Starting\n");

	printf("Parent Process Ending\n");

}
