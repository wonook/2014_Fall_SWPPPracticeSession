require 'minitest/autorun'
require_relative '../impl/Multiset'

class MultisetTest < Minitest::Test
  def setup
    @set = Multiset.new(1)
  end

  def test_contains
    assert_equal true, @set.contains(1)
  end

  def test_add
    @set.add(2)
    assert_equal true, @set.contains(2)
  end

  def test_remove
    @set.remove(1)
    assert_equal false, @set.contains(1)
  end

  def test_duplicate
    @set.add(1)
    assert_equal true, @set.contains(1)
    @set.remove(1)
    assert_equal true, @set.contains(1)
    @set.remove(1)
    assert_equal false, @set.contains(1)
  end
end
