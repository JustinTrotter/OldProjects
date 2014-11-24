--[[ 
JUSTIN TROTTER 10144892

Arithmetic Expression Tree Program Skeleton
     Recursive Function Version with List-style Nodes
     H. Conrad Cunningham, Professor
     Computer and Information Science
     University of Mississippi

Developed for CSci 658, Software Language Engineering, Fall 2013

1234567890123456789012345678901234567890123456789012345678901234567890

2013-08-29: Modified program from author's Scala functional version
2013-09-02: Completed prototype	   
2013-09-04: Made similar to Recursive Function Record version
            added valToString as alternative to treeConcat output
2013-09-07: Corrected typos and comments
2014-11-11: Added tree constructors (as in current Recursive Function
            Record version) and isExp to check expression structure

--]]


--[[ ARITHMETIC EXPRESSION TREES

This program represents an arithmetic expression tree by a list-style
table whose first element is an operator tag and whose subsequent
elements are the operands for that operation.  Each operand may be an
arithmetic expression tree.

--]]


-- Constants for tree node type tags
local CONST_TYPE, CONST_STR = "Const", "Const"
local VAR_TYPE,   VAR_STR   = "Var", "Var"
local SUM_TYPE,   SUM_STR   = "Sum", "Sum"
local SUB_TYPE,   SUB_STR   = "Sub", "Sub"
local PROD_TYPE,  PROD_STR  = "Prod", "Prod"
local DIV_TYPE,   DIV_STR   = "Div", "Div"
local NEG_TYPE,   NEG_STR   = "Neg", "Neg"
local SIN_TYPE,   SIN_STR   = "Sin", "Sin"
local COS_TYPE,   COS_STR   = "Cos", "Cos"

-- Checking for valid expressions

local tags = { [CONST_TYPE] = true, [VAR_TYPE] = true, 
               [SUM_TYPE]   = true, [SUB_TYPE] = true, 
	       [PROD_TYPE]  = true, [DIV_TYPE] = true,
               [NEG_TYPE]   = true, [SIN_TYPE] = true,
               [COS_TYPE]   = true }

local function isExp(t)
  return type(t) == "table" and tags[t[1]] ~= nil and #t >= 2
end


-- Tree node constructor functions

local function makeConst(v)
  if type(v) == "number" then 
    return {CONST_TYPE, v}
  else
    error("makeConst called with nonumeric value field: " .. 
          tostring(v), 2)
 end
end

local function makeVar(n)
  if type(n) == "string" then
    return {VAR_TYPE, n}
  else
    error("makeVar called with nonstring name argument: " .. 
          tostring(n), 2)
  end
end

local function makeSum(l,r)
  if isExp(l) then
    if isExp(r) then
      return {SUM_TYPE, l, r}
    else
      error("Second argument of makeSum is not a valid expression: " 
            .. tostring(r), 2)
    end
  else
    error("First argument of makeSum is not a valid expression: " ..
          tostring(l), 2)
  end
end

local function makeSub(l,r)
  if isExp(l) then
    if isExp(r) then
      return {SUB_TYPE, l, r}
    else
      error("Second argument of makeSub is not a valid expression: "
            .. tostring(r), 2)
    end
  else
    error("First argument of makeSub is not a valid expression: " ..
          tostring(l), 2)
  end
end

local function makeProd(l,r)
  if isExp(l) then
    if isExp(r) then
      return {PROD_TYPE, l, r}
    else
      error("Second arguement of makeProd is not a valid expression: "
            .. tostring(r), 2)
    end
  else
    error("First argument of makeProd is not a valid expression: "..
          tostring(l), 2)
  end
end

local function makeDiv(l,r)
  if isExp(l) then
    if isExp(r) then
      return {DIV_TYPE, l, r}
    else
      error("Second arguement of makeDiv is not a valid expression: "
           .. tostring(r), 2)
    end
  else
    error("First arguement of makeDiv is not a valid expression: "..
           tostring(l), 2)
  end
end

local function makeNeg(v,w)
  if isExp(v) then
    if isExp(w) then
      return {NEG_TYPE, v, w}
    else
      return {NEG_TYPE, v}
    end
  else
    error("Arguement of makeNeg is not a valid expression: " ..
          tostring(v), 2)
  end
end

local function makeSin(v)
  if isExp(v) then
    return {SIN_TYPE, v}
  else
    error("Arguement of makeSin is not a valid expression: " ..
          tostring(v), 2)
  end
end

local function makeCos(v)
  if isExp(v) then
    return {COS_TYPE, v}
  else
    error("Arguement of makeCos is not a valid expression: " ..
          tostring(v), 2)
  end
end

-- Constants for frequent constant expression trees (singleton refs)
local CONST_ZERO = makeConst(0)
local CONST_ONE  = makeConst(1)


-- Function "eval" evaluates expression tree "t" in environment
-- "env". It checks the tag (first element of "t") to determine
-- what actions to take.

local function eval(t,env)
  if isExp(t) then
    if type(env) == "table" then
      if t[1] == SUM_TYPE then 
        return eval(t[2],env) + eval(t[3],env)
      elseif t[1] == SUB_TYPE then
        return eval(t[2],env) - eval(t[3],env)
      elseif t[1] == PROD_TYPE then
        return eval(t[2],env) * eval(t[3],env)
      elseif t[1] == DIV_TYPE then
        return eval(t[2],env) / eval(t[3],env)
      elseif t[1] == NEG_TYPE then
        return eval(t[2],env) * -1
      elseif t[1] == SIN_TYPE then
        return math.sin(eval(t[2],env))
      elseif t[1] == COS_TYPE then
        return math.cos(eval(t[2],env))
      elseif t[1] == VAR_TYPE then
        return env[t[2]]
      elseif t[1] == CONST_TYPE then        
        return t[2]
      else
        error("eval called with unknown tree type tag: " .. 
              tostring(t[1]), 2)
      end
    else
      error("eval called with invalid environment argument: " .. 
            tostring(env), 2)
    end
  else
    error("eval called with invalid expression argument: " .. 
          tostring(t), 2)
  end
end


-- Function "derive" takes an arithmetic expression tree "t" and a
-- variable "v" and returns the derivative, another arithmetic
-- expression tree.

local function derive(t,v)
  if isExp(t) then
    if type(v) == "string" then
      if t[1] == SUM_TYPE then
        return makeSum(derive(t[2],v), derive(t[3],v))
      elseif t[1] == SUB_TYPE then
        return makeSub(derive(t[2],v), derive(t[3],v))
      elseif t[1] == PROD_TYPE then
        return makeSum(
                 makeProd(derive(t[2],v), t[3]), 
                 makeProd(derive(t[3],v), t[2]))
      elseif t[1] == DIV_TYPE then
        return makeDiv(
                 makeSub(
                   makeProd(t[3], derive(t[2],v)), 
                   makeProd(t[2], derive(t[3],v))),
                 makeProd(t[3], t[3]))
      elseif t[1] == NEG_TYPE then
        if t[3] == Nil then
          return makeNeg(derive(t[2],v))
        else return makeNeg(derive(t[2],v), derive(t[3],v))
        end
      elseif t[1] == SIN_TYPE then
        return makeProd(
                 makeCos(t[2],v),
                 derive(t[2],v))
      elseif t[1] == COS_TYPE then
        return makeProd(
                 makeNeg(makeSin(t[2],v)),
                 derive(t[2],v))
      elseif t[1] == VAR_TYPE then
        if v == t[2] then     
          return CONST_ONE
        else
          return CONST_ZERO
        end
      elseif t[1] == CONST_TYPE then
        return CONST_ZERO
      else
        error("derive called with unknown tree type tag: " .. 
              tostring(t.tag), 2)
      end
    else
      error("derive called with invalid variable: " .. 
            tostring(v), 2)
    end
  else
    error("derive called with invalid expression argument: " ..
          tostring(t), 2)
  end
end

local function simplify(t)
  if isExp(t) then
    if t[1] == VAR_TYPE or t[1] == CONST_TYPE then 
      return t
    elseif t[1] == NEG_TYPE then
      if t[3] == Nil then
        return makeNeg(simplify(t[2]))
      else
        error("Neg called with more than one arguement: " ..
          tostring(t[3]),2)
      end
    elseif t[1] == SIN_TYPE then
      if t[3] == Nil then
        return makeSin(simplify(t[2]))
      else
        error("Sin called with more than one arguement: " ..
          tostring(t[3]),2)
      end
    elseif t[1] == COS_TYPE then
      if t[3] == Nil then
        return makeCos(simplify(t[2]))
      else
        error("Cos called with more than one arguement: " ..
          tostring(t[3]),2)
      end
    elseif t[2][1] == CONST_TYPE and t[3][1] == CONST_TYPE then
      if t[1] == SUM_TYPE then
        return makeConst(t[2][2] + t[3][2])
      elseif t[1] == SUB_TYPE then
        return makeConst(eval(t[2],env) - eval(t[3],env))
      elseif t[1] == PROD_TYPE then
        return makeConst(eval(t[2],env) * eval(t[3],env))
      elseif t[1] == DIV_TYPE then
        return makeConst(eval(t[2],env) / eval(t[3],env))
      else
        error("simplify called with unknown type: " ..
              tostring(t[1],2))
      end
    elseif t[2][1] == VAR_TYPE then
      if t[1] == SUM_TYPE then
        return makeSum(t[2], simplify(t[3]))
      elseif t[1] == SUB_TYPE then
        return makeSub(t[2], simplify(t[3]))
      elseif t[1] == PROD_TYPE then
        return makeProd(t[2], simplify(t[3]))
      elseif t[1] == DIV_TYPE then
        return makeDiv(t[2], simplify(t[3]))
      else
        error("simplify called with unknown type: " ..
          tostring(t[1],2))
      end
    elseif t[3][1] == VAR_TYPE then
      if t[1] == SUM_TYPE then
        return makeSum(simplify(t[2]), t[3])
      elseif t[1] == SUB_TYPE then
        return makeSub(simplify(t[2]), t[3])
      elseif t[1] == PROD_TYPE then
        return makeProd(simplify(t[2]), t[3])
      elseif t[1] == DIV_TYPE then
        return makeDiv(simplify(t[2]), t[3])
      else 
        error("simplify called with unknown type: " ..
          tostring(t[1],2))
      end
    elseif t[1] == SUM_TYPE then
      if simplify(t[2])[1] == CONST_TYPE and 
         simplify(t[3])[1] == CONST_TYPE then
        return makeConst(simplify(t[2])[2] +
                         simplify(t[3])[2])
      else
        return makeSum(simplify(t[2]), simplify(t[3]))
      end
    elseif t[1] == SUB_TYPE then
      if simplify(t[2])[1] == CONST_TYPE and
         simplify(t[3])[1] == CONST_TYPE then
        return makeConst(simplify(t[2])[2] -
                         simplify(t[3])[2])
      else
        return makeSub(simplify(t[2]), simplify(t[3]))
      end
    elseif t[1] == PROD_TYPE then
      if simplify(t[2])[1] == CONST_TYPE and
         simplify(t[3])[1] == CONST_TYPE then
        return makeConst(simplify(t[2])[2] *
                         simplify(t[3])[2])
      else
        return makeProd(simplify(t[2]), simplify(t[3]))
      end
    elseif t[1] == DIV_TYPE then
      if simplify(t[2])[1] == CONST_TYPE and
         simplify(t[3])[1] == CONST_TYPE then
        return makeConst(simplify(t[2])[2] /
                         simplify(t[3])[2])
      else
        return makeDiv(simplify(t[2]), simplify(t[3]))
      end 
    else
      error("simplify called with unknown type: " ..
        tostring(t[1]),2)
    end
  else
    error("simplify called with invalid expression: " ..
      tostring(t[1]),2)
  end
end

local function getType(t)
  if isExp(t) then
    if t[1] == CONST_TYPE then
      return makeConst(t[2])
    else
      error("test error")
    end
  end
end

-- Function "valToString" takes an arithmetic expression tree "t" and
-- returns a string representation of the expression tree.

local function valToString(t)
  if isExp(t) then
    if t[1] == SUM_TYPE then 
      return SUM_STR .. "(" .. valToString(t[2]) .. "," 
                            .. valToString(t[3]) .. ")"
    elseif t[1] == SUB_TYPE then
      return SUB_STR .. "(" .. valToString(t[2]) .. ","
                            .. valToString(t[3]) .. ")"
    elseif t[1] == PROD_TYPE then
      return PROD_STR .. "(" .. valToString(t[2]) .. ","
                             .. valToString(t[3]) .. ")"
    elseif t[1] == DIV_TYPE then
      return DIV_STR .. "("  .. valToString(t[2]) .. ","
                             .. valToString(t[3]) .. ")"
    elseif t[1] == NEG_TYPE then
      if t[3] == Nil then
        return NEG_STR .. "("  .. valToString(t[2]) .. ")"
      else
        return NEG_STR .. "("  .. valToString(t[2]) .. ","
                               .. valToString(t[3]) .. ")"
      end
    elseif t[1] == SIN_TYPE then
      return SIN_STR .. "("  .. valToString(t[2]) .. ")"
    elseif t[1] == COS_TYPE then
      return COS_STR .. "("  .. valToString(t[2]) .. ")"
    elseif t[1] == VAR_TYPE then
      return VAR_STR .. "(" .. t[2] .. ")"
    elseif t[1] == CONST_TYPE then
      return CONST_STR .. "(" .. tostring(t[2]) .. ")"
    else
      error("valToString called with unknown tree type: " ..
            tostring(t[1]), 2)
    end
  else
    error("valToString called with invalid expression: " ..
          tostring(t), 2)
  end
end


-- MAIN PROGRAM
print("**********************************************")
local exp = makeNeg( 
              makeDiv(
                     makeSum(makeConst(4),makeConst(3)), 
                     makeSub(makeVar("x"),makeVar("y")) ))
local env = { x = 5, y = 7 }

print("Expression: " .. valToString(exp))
print("Simplify: " .. valToString(simplify(exp)))
print("Evaluation with x=5, y=7: " .. eval(exp,env))
print("Derivative relative to x:\n  " .. 
      valToString(derive(exp, "x")))

print("Derivative relative to y:\n  " .. 
      valToString(derive(exp, "y")))

print("**********************************************")

local exp2 = makeNeg(makeProd(makeConst(1),makeVar("y")))
local env2 = { x = 5, y = 7 }

print("Expression: " .. valToString(exp2, env2))
print("Evaluation with x=5, y=7: " .. eval(exp2,env2)) 
print("Derivative relative to x:\n  " ..
      valToString(derive(exp2, "x")))
print("Derivative relative to y:\n  " ..
      valToString(derive(exp2, "y")))

print("Simplify: " .. valToString(simplify(exp2)))

print("**********************************************")

local exp3 = makeSin(makeDiv(makeConst(2), makeSum(makeVar("y"),makeVar("x"))))
local env3 = { x = 5, y = 7 }

print("Expression: " .. valToString(exp3, env3))
print("Simplify: " .. valToString(simplify(exp3)))
print("Evaluation with x=5, y=7: " .. eval(exp3,env3))
print("Derivative relative to x:\n  " ..
      valToString(derive(exp3, "x")))
print("Derivative relative to y:\n  " ..
      valToString(derive(exp3, "y")))

print("**********************************************")

local exp4 = makeCos(makeSum(makeConst(1),makeVar("y")))
local env4 = { x = 5, y = 7 }

print("Expression: " .. valToString(exp4, env4))
print("Simplify: " .. valToString(simplify(exp4)))
print("Evaluation with x=5, y=7: " .. eval(exp4,env4))
print("Derivative relative to x:\n  " ..
      valToString(derive(exp4, "x")))
print("Derivative relative to y:\n  " ..
      valToString(derive(exp4, "y")))

print("**********************************************")
