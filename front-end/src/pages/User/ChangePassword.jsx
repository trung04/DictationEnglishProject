const ChangePassword = () => {
  return (
    <>
      <main className="container-lg">
        <div className="message"></div>

        <h1 className="mb-4">Change password</h1>

        <div className="row">
          <div className="col-lg-4">
            <div className="p-3 border shadow">
              <form
                name="change_password"
                method="post"
                novalidate="novalidate"
              >
                <div id="change_password" novalidate="novalidate">
                  <div className="mb-3">
                    <label
                      for="change_password_newPassword"
                      className="form-label required"
                    >
                      New password
                    </label>
                    <input
                      type="password"
                      id="change_password_newPassword"
                      name="change_password[newPassword]"
                      required="required"
                      className="form-control"
                    />{" "}
                  </div>
                  <div className="mb-3">
                    <button
                      type="submit"
                      id="change_password_submit"
                      name="change_password[submit]"
                      className="btn-primary btn"
                    >
                      Submit
                    </button>
                  </div>
                  <input
                    type="hidden"
                    id="change_password__token"
                    name="change_password[_token]"
                    value="2974e.hPQ5wNXeERTo1ZHIsKdzI_6UJxyL2egFHoPD35WYO38.t5pJl6bmcHOeotSG0f49E5WjTnv-o7lgac_6p97BeTfDjUytmZV8V7K7qQ"
                  />
                </div>
              </form>
            </div>
          </div>
        </div>
      </main>
    </>
  );
};
export default ChangePassword;
