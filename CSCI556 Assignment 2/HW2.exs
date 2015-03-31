#Justin Trotter 10144892
#How to run:
#Place code in working directory
#Open iex
# >> c "HW2.exs"
# >> HW2.test()

defmodule HW2 do
	# Use this to run each test at once
	# 
	def test do
		IO.puts "Begin HW2"
		IO.puts " "
		fizzbuzz()
		prefix()
		sumTest()
		guessTest()
		mapSumTest()
		maxTest()
		splitTest()
		allTest()
		eachTest()
		IO.puts "End HW2"
	end
	
	# prints FizzBuss if first and second args are 0 repectively
	# if neither are 0, then prints 3rd arg
	def fizzbuzz do
		IO.puts "Chapter 5 exercise 2:"
		_fizzbuzz = fn 
			(0, 0, _) -> "FizzBuzz"
			(0, _, _) -> "Fizz"
			(_, 0, _) -> "Buzz"
			(_, _, c) -> c
		end

		IO.puts ">> _fizzbuzz.(0,0,3)"
		IO.puts _fizzbuzz.(0,0,3)
		IO.puts ">> _fizzbuzz.(0,2,3)"
		IO.puts _fizzbuzz.(0,2,3)
		IO.puts ">> _fizzbuzz.(1,0,3)"
		IO.puts _fizzbuzz.(1,0,3)
		IO.puts ">> _fizzbuzz.(1,2,3)"
		IO.puts _fizzbuzz.(1,2,3)
		IO.puts " "
	end
	
	# a Function in a function example
	def prefix do
		IO.puts "Chapter 5 exercise 4:"
		_prefix = fn 
			pre -> (fn 
				name -> "#{pre} #{name}" 
			end) 
		end

		IO.puts ">> dr = _prefix.(\"Dr.\")"
		dr = _prefix.("Dr.")
		IO.puts ">> dr.(\"Cunningham\")"
		IO.puts dr.("Cunningham")
		IO.puts ">> _prefix.(\"Mr.\").(\"Trotter\")"
		IO.puts _prefix.("Mr.").("Trotter")
		IO.puts " "
	end

	# generic summation function
	def sum(0), do: 0
	def sum(n) when n > 0 do 
		n + sum(n-1)
	end
	def sum(n) when n < 0 do
		n + sum(n+1)
	end
	
	# tests summation function
	def sumTest do
		IO.puts "Chapter 6 exercise 4:"
		IO.puts ">> sum(1)"
		IO.puts sum(1)
		IO.puts ">> sum(2)"
		IO.puts sum(2)
		IO.puts ">> sum(5)"
		IO.puts sum(5)
		IO.puts ">> sum(10)"
		IO.puts sum(10)
		IO.puts ">> sum(-1)"
		IO.puts sum(-1)
		IO.puts ">> sum(-5)"
		IO.puts sum(-5)
		IO.puts " "
		
	end
	
	# a short number guessing game
	# uses a sort of sequential search to find
	def guess(actual,a..b) when a + div(b-a,2) > actual do
		IO.puts "Is it #{a + div(b-a,2)} LESS"
		guess(actual,a..a + div(b-a,2))
	end
	def guess(actual,a..b) when a + div(b-a,2) < actual do
		IO.puts "Is it #{a + div(b-a,2)} GREATER"
		guess(actual, a + div(b-a,2)..b)
	end
	def guess(actual,a..b) when a + div(b-a,2) == actual do
		IO.puts "It is #{a + div(b-a,2)}"
		IO.puts " "
	end
	
	# tests guessing game
	def guessTest do
		IO.puts "Chapter 6 exercise 6:"
		IO.puts ">> guess(17, 1..100)"
		guess(17, 1..100)
		IO.puts ">> guess(256, 1..1000)"
		guess(256, 1..1000)
		IO.puts ">> guess(347, 250..500)"
		guess(347, 250..500)
	end
	
	# maps a function to each element then sums each element
	def sumList([], total), do: total
	def sumList([head|tail],total), do: sumList(tail, head+total)
	
	def mapfunc([], _func), do: []
	def mapfunc([head|tail], _func), do: [_func.(head)|mapfunc(tail, _func)]
	
	def mapSum(list, func) do
		sumList(mapfunc(list, func),0)
	end
	
	# test mapSum
	def mapSumTest do
		IO.puts "Chapter 7 exercise 1:"
		IO.puts ">> mapSum([1,2,3], &(&1 * &1))"
		IO.puts mapSum([1,2,3], &(&1 * &1))
		IO.puts " "
	end
	
	# finds max number in list
	def max([head|tail]) do
		_max([head|tail],head)
	end

	defp _max([],m) do
		m
	end
	defp _max([head|tail],m) when head > m do
		_max(tail,head)
	end
	defp _max([head|tail],m) when head <= m do
		_max(tail,m)
	end
	
	# tests finding max number includes negative numbers
	def maxTest do
		IO.puts "Chapter 7 exercise 2:"
		IO.puts ">> max([1,2,3,4,5,6,7,-8,9,10])"
		IO.puts max([1,2,3,4,5,6,7,-8,9,10])
		IO.puts ">> max([10,9,8,7,6,-5,4,3,2,1])"
		IO.puts max([10,9,8,7,6,-5,4,3,2,1])
		IO.puts " "
	end
	
	# splits a list by the nth element, n = count
	# returns in the following form:
	# {[listA],[listB]}
	def split(list,count) do
		{findHead(list,count),findTail(list,count)}
	end
	
	defp findHead([],_) do 
		[]
	end
	
	defp findHead([head|tail],count) when count > 0 do
		[head] ++ findHead(tail,count-1)
	end
	
	defp findHead([_head|_tail],count) when count <= 0 do
		[]
	end
	
	defp findTail([],_) do
		[]
	end
	
	defp findTail([_head|tail], count) when count > 0 do
		findTail(tail, count-1)
	end
	
	defp findTail(list,count) when count <= 0 do
		list
	end
	
	# tests splitting function
	def splitTest() do
		IO.puts "Chapter 10 exercise 5: split"
		IO.puts "split([1,2,3,4,5],3)"
		IO.inspect split([1,2,3,4,5],3)
		
		IO.puts "split([1,2,3,4,5],7)"
		IO.inspect split([1,2,3,4,5],7)
		IO.puts " "
	end
	
	#checks to see if all members of a list return true when function is applied
	def all?([], _func) do
		false
	end
	
	def all?(list,func) do
		_all?(list,func,true)
	end
	
	defp _all?([],_func, _bool) do
		true
	end
	
	defp _all?([head|tail],func, bool) do
		truth = fn
			false -> false
			true -> _all?(tail, func, func.(head))
		end
		truth.(bool)
	end
	
	# tests the all? function
	def allTest() do
		IO.puts "Chapter 10 exercise 5: all?"
		IO.puts "all?([1,2,3,4,5], &(&1<4))"
		IO.puts all?([1,2,3,4,5], &(&1<4))
		IO.puts "all?([1,2,3,4,5], &(&1<6))"
		IO.puts all?([1,2,3,4,5], &(&1<6))
		IO.puts " "
	end
	
	# applys function to each of the lists elements
	def each([], _func) do
		[]
	end
	
	def each([head|tail], func) do
		func.(head)
		each(tail,func)
	end
	
	# tests each function
	def eachTest() do
		IO.puts "Chapter 10 exercise 5: each"
		IO.puts "each([\"some\", \"example\"], fn(x) -> IO.puts x end)"
		each(["some", "example"], fn(x) -> IO.puts x end)
		IO.puts " "
		
	end
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
end