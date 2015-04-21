class DateCalculator

  def self.convert(d) # static method. not good!
    y = 1980
    while (d > 365) do
      if (y % 400 == 0 ||
          (y % 4 == 0 && y % 100 != 0))
        if (d > 366)
          d -= 366
          y += 1
        end
      else
        d -= 365
        y += 1
      end
    end
    return y
  end

end


if __FILE__ == $0
    puts DateCalculator.convert(ARGV[0].to_i)
end

# pseudo code

# start with Year = 1980
# while (days remaining > 365)
#   if Year is a leap year
#       then if possible, peel off 366 days and advance Year by 1
#   else
#       peel off 365 days and advance Year by 1
# return Year
