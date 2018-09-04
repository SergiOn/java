import { LoginAppPage } from './app.po';

describe('login-app App', () => {
  let page: LoginAppPage;

  beforeEach(() => {
    page = new LoginAppPage();
  });

  it('should display message saying app works', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('app works!');
  });
});
