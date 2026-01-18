import { ActiveCountPipe } from './active-count.pipe';

describe('ActiveCountPipe', () => {
  it('create an instance', () => {
    const pipe = new ActiveCountPipe();
    expect(pipe).toBeTruthy();
  });
});
