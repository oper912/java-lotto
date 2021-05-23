package lotto.controller;

import lotto.domain.Lotto;
import lotto.domain.LottoGame;
import lotto.domain.LottoStatistics;
import lotto.generator.LottoNumberGenerator;
import lotto.generator.NumberGenerator;
import lotto.view.InputView;
import lotto.view.ResultView;

public class LottoController {
    private final NumberGenerator numberGenerator;
    private final LottoStatistics lottoStatistics;

    public LottoController() {
        this.numberGenerator = new LottoNumberGenerator();
        this.lottoStatistics = new LottoStatistics();
    }

    public void play() {
        LottoGame lottoGame = LottoGame.init(InputView.inputAmount());
        lottoGame.buyLotto(numberGenerator);
        ResultView.printLottoes(lottoGame);

        Lotto winnerLotto = Lotto.from(InputView.inputWinnerLottoNumbers());
        lottoStatistics.init(lottoGame, winnerLotto);

        ResultView.printStatistics(lottoStatistics);
    }
}
