Given /^I logged in as "(\w+)" with "(\w+)" "(\w+)" times$/ do |name, pwd, cnt|
  user = User.new({username: name, password: pwd, counter: cnt.to_i})
  user.save
end
Then /^I see my counter at "(\w+)"$/ do |cnt|
  assert page.has_content?("You've logged in #{cnt} time(s)!")
end
