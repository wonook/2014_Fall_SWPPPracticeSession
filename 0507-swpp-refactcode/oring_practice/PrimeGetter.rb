class PrimeGetter
  def self.prime_not_upper_than(n)
    unless n.is_a? Integer
      puts "n must be an integer."
      return nil
    end
    if n < 0
      puts "n must be greater than 0."
      return nil
    end
    ar = [2]
    i = 3
    while (i < n) do
      ip = true
      ar.each do |a|
        if (i % a == 0)
          ip = false
          break
        elsif (a > Math.sqrt(i))
          break
        end
      end
      if(ip)
        ar.push(i)
      end
      i = i+1
    end 
    return ar
  end

end

if __FILE__ == $0
  puts PrimeGetter.prime_not_upper_than(ARGV[0].to_i)
end

# Get prime numbers not upper than maximum number
#
# pseudo code

# if maximum number is not a integer or lower than 1
#   print error message and return nil
#
# start with prime numbers = [2] and number = 3
# while (number is not upper than maximum number)
#   if number is prime number
#     add the number to prime numbers
#   increase the number by 1
# return prime numbers
