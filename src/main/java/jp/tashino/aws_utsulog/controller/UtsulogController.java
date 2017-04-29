package jp.tashino.aws_utsulog.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jp.tashino.aws_utsulog.model.ListDataModel;
import jp.tashino.aws_utsulog.model.UtsulogModel;

@Controller
public class UtsulogController {


	private List<ListDataModel> getBigQuestionLists(){
		List<ListDataModel> bigQuestionLists = new ArrayList<ListDataModel>();

		bigQuestionLists.add(new ListDataModel("憂鬱な気分が続いている","big1"));
		bigQuestionLists.add(new ListDataModel("何に対しても興味や喜びの気持ちが起きない","big2"));
		bigQuestionLists.add(new ListDataModel("疲れが取れない、疲れやすい","big3"));

		return bigQuestionLists;
	}

	private List<ListDataModel> getSmallQuestionLists(){
		List<ListDataModel> smallQuestionLists = new ArrayList<ListDataModel>();

		smallQuestionLists.add(new ListDataModel("集中力と注意力が落ちた","small1"));
		smallQuestionLists.add(new ListDataModel("「生きている価値がない」「周りに迷惑をかけている」などと無価値観や罪責感がある","small2"));
		smallQuestionLists.add(new ListDataModel("将来に対する希望のない悲観的な見方","small3"));
		smallQuestionLists.add(new ListDataModel("自分を傷つけたり自殺したくなる考えや実際に行為を行う事","small4"));
		smallQuestionLists.add(new ListDataModel("睡眠がとれない","small5"));
		smallQuestionLists.add(new ListDataModel("食欲がない","small6"));

		return smallQuestionLists;
	}

	@RequestMapping(value="/",method=RequestMethod.GET)
	public String check(Model model){
		UtsulogModel mModel = new UtsulogModel();
		model.addAttribute("utsulogModel", mModel);
		model.addAttribute("bigQuestionLists",getBigQuestionLists());
		model.addAttribute("smallQuestionLists",getSmallQuestionLists());
		return "utsulogRegistration";

	}

	@RequestMapping(value="/",method=RequestMethod.POST)
	public String confirm(@ModelAttribute UtsulogModel mModel,Model model){


		if(mModel.getName()=="" || mModel.getAge()=="" || mModel.isAgreement()==false){
			String tmpComment = "★★名前、年齢を入れてください、\n★★同意事項に同意してください。\n";

			tmpComment = tmpComment.replaceAll("\n", "<br />");
			model.addAttribute("comment",tmpComment);
			model.addAttribute("bigQuestion","判定不能");
			model.addAttribute("smallQuestion","判定不能");
			return "utsulogConfirm";
		}


		String[] selectedBigQuestion = mModel.getBigQuestion();
		Map<String,String> bigQuestionLists = new HashMap<String,String>();

		bigQuestionLists.put("big1", "集中力と注意力が落ちた");
		bigQuestionLists.put("big2", "「生きている価値がない」「周りに迷惑をかけている」などと無価値観や罪責感がある");
		bigQuestionLists.put("big3", "将来に対する希望のない悲観的な見方");

		String mbLists ="";
		for(String data:selectedBigQuestion){
			mbLists += bigQuestionLists.get(data)+"<br />";
		}
		model.addAttribute("bigQuestion",mbLists);

		int bigCount = selectedBigQuestion.length;




		String[] selectedSmallQuestion = mModel.getSmallQuestion();
		Map<String,String> smallQuestionLists = new HashMap<String,String>();

		smallQuestionLists.put("small1", "集中力と注意力が落ちた");
		smallQuestionLists.put("small2", "「生きている価値がない」「周りに迷惑をかけている」などと無価値観や罪責感がある");
		smallQuestionLists.put("small3", "将来に対する希望のない悲観的な見方");
		smallQuestionLists.put("small4", "自分を傷つけたり自殺したくなる考えや実際に行為を行う事");
		smallQuestionLists.put("small5", "睡眠がとれない");
		smallQuestionLists.put("small6", "食欲がない");

		String msLists ="";
		for(String data:selectedSmallQuestion){
			msLists += smallQuestionLists.get(data)+"<br />";
		}
		model.addAttribute("smallQuestion",msLists);

		int smallCount = selectedSmallQuestion.length;

		String judge;
		if(bigCount >=3 && smallCount>=4){
			judge = "うつが強いの可能性があります。\n\n";
		} else if(bigCount >=2 && smallCount>=4){
			judge = "うつが中くらいの可能性があります。\n\n";
		} else if(bigCount >= 2 && smallCount>=2){
			judge = "軽いうつの可能性があります。\n\n";
		} else {
			judge = "うつがない可能性があります。\n\n";
		}

		String ageComment = "";
		boolean exceptionSwitch = false;
		int age = 0;
		try{
			age = Integer.parseInt(mModel.getAge());
		} catch (NumberFormatException e){
			ageComment = "★ 年齢欄には０から１２０の整数を入れてくださいね。\n";
			judge = "";
			exceptionSwitch = true;
		}
		if(exceptionSwitch == false){
			if(age<0){
				ageComment = "★年齢欄には１から１２０の整数を入れてくださいね。\n";
			}else if (age < 25){
				ageComment = "0-24歳のあなたは、人生の基礎固めのじきですが無理をせず、気楽に行きましょう\n";
			}else if (age < 35){
				ageComment = "25-34歳のあなたは、人生の伴侶を得ているかもしれませんね、責任も重くなるが無理をせず、気楽に行きましょう\n";
			}else if (age < 50){
				ageComment = "35-49歳のあなたは、親の介護や子供の教育、仕事の責任など、人生で一番大変な時期かもしれませんね、温泉旅行など息抜きを忘れずに\n";
			}else if (age < 121){
				ageComment = "50-121歳のあなたは、私より年上です。どんな人生だったのでしょうか、秋に果実が実る様に、人生の果実が刈り取れるといいですね。\n";
			}else{
				ageComment = "★年齢欄には１から１２０の整数を入れてくださいね。\n";
			}
		}

		String tmpComment = "この結果は医師の診断を代用するものではありません、\n少しでも心の状態が気になったら、医師の直接の診断を受けてください\n\n"
				+ judge + ageComment;

		tmpComment = tmpComment.replaceAll("\n", "<br />");
		model.addAttribute("comment",tmpComment);

		return "utsulogConfirm";
	}
}
