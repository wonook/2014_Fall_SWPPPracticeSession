class Multiset
  attr_accessor :my_set

  def initialize(num)
    @my_set = []
    @my_set << num
  end

  def contains(n)
    @my_set.include? n
  end

  def add(n)
    @my_set << n
  end

  def remove(n)
    @my_set.delete_at(@my_set.find_index(n))
  end
end
