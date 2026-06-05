from functools import lru_cache

class Solution:
    def totalWaviness(self, num1: int, num2: int) -> int:
        def solve(num_str: str) -> int:
            if not num_str:
                return 0
                
            n = len(num_str)
            
            # Memoized state: (index, tight flag, started flag, last digit, second-to-last digit)
            @lru_cache(None)
            def dp(pos: int, is_tight: bool, is_started: bool, prev1: int, prev2: int) -> int:
                # Base Case: Reached the end of the number string
                if pos == n:
                    return 0
                
                # Determine the upper bound for the current digit
                limit = int(num_str[pos]) if is_tight else 9
                res = 0
                
                for d in range(limit + 1):
                    # Evaluate flags for the next state
                    next_tight = is_tight and (d == limit)
                    next_started = is_started or (d > 0)
                    
                    # Calculate if a peak or valley is formed at 'prev1'
                    # A pattern requires at least 3 digits: prev2 -> prev1 -> d
                    waviness_increment = 0
                    if is_started and prev2 != -1 and prev1 != -1:
                        # Peak condition
                        if prev1 > prev2 and prev1 > d:
                            waviness_increment = 1
                        # Valley condition
                        elif prev1 < prev2 and prev1 < d:
                            waviness_increment = 1
                    
                    # If this digit matches the waviness pattern, it contributes to all valid suffixes downstream.
                    # We need to figure out how many valid number combinations can be formed after this position.
                    # This helper function calculates that combinatorial suffix count.
                    if waviness_increment > 0:
                        res += waviness_increment * count_ways(pos + 1, next_tight)
                        
                    # Transition to the next digit position
                    if not next_started:
                        # Keep padding leading zeros, structural history stays clear
                        res += dp(pos + 1, next_tight, False, -1, -1)
                    else:
                        # Move forward, passing down the last two digits used
                        res += dp(pos + 1, next_tight, True, d, prev1)
                        
                return res

            # Separate simple DP helper to count how many valid suffix combinations exist from 'pos' onward
            @lru_cache(None)
            def count_ways(pos: int, is_tight: bool) -> int:
                if pos == n:
                    return 1
                limit = int(num_str[pos]) if is_tight else 9
                total = 0
                for d in range(limit + 1):
                    total += count_ways(pos + 1, is_tight and (d == limit))
                return total

            return dp(0, True, False, -1, -1)

        # Standard range subtraction for Digit DP: count(num2) - count(num1 - 1)
        ans_num2 = solve(str(num2))
        ans_num1 = solve(str(num1 - 1))
        
        return ans_num2 - ans_num1