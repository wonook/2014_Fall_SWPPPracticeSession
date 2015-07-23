class UsersController < ApplicationController
  before_action :set_user, only: [:show, :edit, :update, :destroy]

  # GET /users
  # GET /users.json
  def index
    @users = User.all
  end

  # GET /users/1
  # GET /users/1.json
  def show
  end

  # GET /users/new
  def new
    @user = User.new
  end

  # GET /users/1/edit
  def edit
  end

  # POST /users
  # POST /users.json
  def create
    if params[:login_button]
      login
    else
      signup
    end
  end

  # PATCH/PUT /users/1
  # PATCH/PUT /users/1.json
  def update
    respond_to do |format|
      if @user.update(user_params)
        format.html { redirect_to @user, notice: 'User was successfully updated.' }
        format.json { render :show, status: :ok, location: @user }
      else
        format.html { render :edit }
        format.json { render json: @user.errors, status: :unprocessable_entity }
      end
    end
  end

  # DELETE /users/1
  # DELETE /users/1.json
  def destroy
    @user.destroy
    respond_to do |format|
      format.html { redirect_to users_url, notice: 'User was successfully destroyed.' }
      format.json { head :no_content }
    end
  end

  def signup
    @user = User.new(user_params)

    respond_to do |format|
      if @user.username.length < 5 || @user.username.length > 20
        @error = "The user name should be 5~20 characters long. Please try again."
        msg = { error_code: -1 }
        format.json { render json: msg }
        format.html { render :new }
      elsif @user.password.length < 8 || @user.password.length >20
        @error = "The password should be 8~20 characters long. Please try again."
        msg = { error_code: -2 }
        format.json { render json: msg }
        format.html { render :new }
      elsif !User.where(username: @user.username).first.nil?
        @error = "This user name already exists. Please try again."
        msg = { error_code: -3 }
        format.json { render json: msg }
        format.html { render :new }
      elsif @user.save
        @user.logincount+=1
        @user.save
        session[:userid] = @user.id
        msg = { user_name: @user.username, login_count: @user.logincount }
        format.json { render json: msg }
        format.html { redirect_to @user, notice: 'User was successfully created.' }
        # format.json { render :show, status: :created, location: @user }
      else
        format.json { render json: @user.errors, status: :unprocessable_entity }
        format.html { render :new }
      end
    end
  end

  def login
    @user = User.new(user_params)

    u = User.where(username: @user.username).first

    respond_to do |format|
      if u.nil? #username doesn't exist
        @error = "Invalid username and password combination. Please try again."
        @user.errors.add(:username, "doesn't exist")
        msg = { error_code: -4 }
        format.json { render json: msg }
        format.html { render :new }
        # format.json { render json: u.errors, status: :unprocessable_entity  }
      elsif u.password == @user.password #password is correct
        u.logincount+=1
        u.save
        session[:userid] = u.id
        msg = { user_name: @user.username, login_count: u.logincount }
        format.json { render json: msg }
        format.html { redirect_to u, notice: 'Log in complete.' }
      else #password incorrect
        @error = "Invalid username and password combination. Please try again."
        @user.errors.add(:password, "incorrect")
        msg = { error_code: -4 }
        format.json { render json: msg }
        format.html { render :new }
        # format.json { render json: u.errors, status: :unprocessable_entity  }
      end
    end
  end

  def logout
    reset_session
    redirect_to "/users/new"
  end

  def destroyall
    reset_session
    @user = User.all
    @user.each do |u|
      u.destroy
    end
    respond_to do |format|
      msg = {}
      format.json {render json: msg}
    end
  end

  private
    # Use callbacks to share common setup or constraints between actions.
    def set_user
      @user = User.find(params[:id])
    end

    # Never trust parameters from the scary internet, only allow the white list through.
    def user_params
      params[:user]
      params.require(:user).permit(:username, :password)
    end
end
