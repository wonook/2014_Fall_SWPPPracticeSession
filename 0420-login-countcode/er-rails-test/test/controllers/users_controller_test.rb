require 'test_helper'

class UsersControllerTest < ActionController::TestCase
  test 'not actually a test' do
    assert_not false
  end
  test '#signup_success' do
    post :signup, {'username' => 'testuser', 'password' => 'testpass'}
    json = JSON.parse(response.body)
    assert json['user_name'] == 'testuser'
    assert json['login_count']==1
  end
  test '#signup_failure_user' do
    post :signup, {'username' => 'test', 'password' => 'testpass'}
    json = JSON.parse(response.body)
    assert json['error_code'] == -1
  end
  test '#signup_failure_pwd' do
    post :signup, {'username' => 'testuser', 'password' => 'test'}
    json = JSON.parse(response.body)
    assert json['error_code'] == -2
  end
  test '#signup_failure_dupl' do
    post :signup, {'username' => 'testuser', 'password' => 'testpass'}
    json = JSON.parse(response.body)
    assert json['user_name'] == 'testuser'
    assert json['login_count'] == 1

    post :signup, {'username' => 'testuser', 'password' => 'testfail'}
    json = JSON.parse(response.body)
    assert json['error_code'] == -3
  end
  test '#signup_failure_dupl2' do
    post :signup, {'username' => 'Hilary', 'password' => 'testfail'}
    json = JSON.parse(response.body)
    assert json['error_code'] == -3
  end
  test '#login_success' do
    post :login, {'username' => 'Hilary', 'password' => 'iloveclinton'}
    json = JSON.parse(response.body)
    assert json['user_name'] == 'Romeo'
    assert json['login_count'] == 101
  end
  # test "the truth" do
  #   assert true
  # end
end
