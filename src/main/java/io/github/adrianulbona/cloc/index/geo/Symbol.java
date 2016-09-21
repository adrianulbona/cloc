package io.github.adrianulbona.cloc.index.geo;

import java.util.Map;

import static java.util.Arrays.stream;
import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toMap;

/**
 * Created by adrianulbona on 30/08/16.
 */
public enum Symbol {
	GS_0('0'),
	GS_1('1'),
	GS_2('2'),
	GS_3('3'),
	GS_4('4'),
	GS_5('5'),
	GS_6('6'),
	GS_7('7'),
	GS_8('8'),
	GS_9('9'),
	GS_b('b'),
	GS_c('c'),
	GS_d('d'),
	GS_e('e'),
	GS_f('f'),
	GS_g('g'),
	GS_h('h'),
	GS_j('j'),
	GS_k('k'),
	GS_m('m'),
	GS_n('n'),
	GS_p('p'),
	GS_q('q'),
	GS_r('r'),
	GS_s('s'),
	GS_t('t'),
	GS_u('u'),
	GS_v('v'),
	GS_w('w'),
	GS_x('x'),
	GS_y('y'),
	GS_z('z'),
	MISSING('_');

	private static final Map<Integer, Symbol> index = stream(values()).collect(toMap(Symbol::raw, identity()));

	public final int base32;

	Symbol(int base32) {
		this.base32 = base32;
	}

	public int raw() {
		return this.base32;
	}

	public static Symbol decode(int base32) {
		if (!index.containsKey(base32)) {
			throw new IllegalArgumentException();
		}
		return index.get(base32);
	}
}
