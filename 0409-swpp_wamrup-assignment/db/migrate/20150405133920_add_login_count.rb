class AddLoginCount < ActiveRecord::Migration
  def change
    add_column :users, :logincount, :integer, :default => 0
  end
end
